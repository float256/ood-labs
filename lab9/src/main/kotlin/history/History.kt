package history

import io.reactivex.rxjava3.subjects.PublishSubject

class History : HistoryManager, HistoryCleaner {
    @Transient
    private var changeHistoryObservableSubject: PublishSubject<Unit> = PublishSubject.create()
    private val history: MutableList<Command> = arrayListOf()
    private var position = -1

    override fun runCommand(command: Command) {
        removeOutOfHistoryCommands()
        command.doAction()
        history.add(command)
        position = history.size - 1
        changeHistoryObservableSubject.onNext(Unit)
    }

    override fun undo() {
        if (!canUndo()) {
            throw UnsupportedOperationException("Command can't' be undone")
        }
        history[position].undoAction()
        position--
        changeHistoryObservableSubject.onNext(Unit)
    }

    override fun redo() {
        if (!canRedo()) {
            throw UnsupportedOperationException("Command can't be redone")
        }
        position++
        history[position].doAction()
        changeHistoryObservableSubject.onNext(Unit)
    }

    override fun canUndo(): Boolean {
        return position >= 0
    }

    override fun canRedo(): Boolean {
        return (history.size > 0) && (position != (history.size - 1))
    }

    override fun doOnHistoryChanged(handler: () -> Unit) {
        changeHistoryObservableSubject.subscribe { handler() }
    }

    private fun removeOutOfHistoryCommands() {
        if (canRedo()) {
            history.subList(position + 1, history.size).clear()
        }
    }

    override fun clean() {
        history.clear()
    }
}
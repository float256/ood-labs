package application.usecase.undo

import history.HistoryManager

class UndoUseCase(
    private val historyManager: HistoryManager,
) {
    fun undo() = historyManager.undo()
    fun redo() = historyManager.redo()
    fun canUndo() = historyManager.canUndo()
    fun canRedo() = historyManager.canRedo()

    fun doOnHistoryChanged(handler: () -> Unit) = historyManager.doOnHistoryChanged(handler)
}

package htmldoc.history

import htmldoc.HtmlDocConstants
import htmldoc.command.Command

class HistoryImpl : History {
    private val history: MutableList<Command> = mutableListOf()
    private var position: Int = START_POSITION

    companion object {
        private const val START_POSITION = -1
    }

    override fun addAndExecuteCommand(command: Command) {
        removeOutOfHistoryCommands()
        command.execute()
        history.add(command)
        position = history.size - 1
    }

    override fun undo() {
        if (!canUndo()) {
            throw UnsupportedOperationException("Command can't' be undone")
        }
        history[position].undo()
        position--
    }

    override fun redo() {
        if (!canRedo()) {
            throw UnsupportedOperationException("Command can't be redone")
        }
        position++
        history[position].execute()
    }

    override fun canUndo(): Boolean {
        return position >= 0
    }

    override fun canRedo(): Boolean {
        return (history.size > 0) && (position != (history.size - 1))
    }

    private fun removeOutOfHistoryCommands() {
        if (canRedo()) {
            history.subList(position + 1, history.size).forEach { it.dispose() }
            history.subList(position + 1, history.size).clear()
        }
        if (history.size > HtmlDocConstants.HISTORY_MAX_LENGTH) {
            history.subList(0, history.size - HtmlDocConstants.HISTORY_MAX_LENGTH).clear()
        }
    }
}
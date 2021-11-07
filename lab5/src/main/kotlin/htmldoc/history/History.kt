package htmldoc.history

import htmldoc.command.Command

interface History {
    fun addAndExecuteCommand(command: Command)
    fun undo()
    fun redo()
    fun canUndo(): Boolean
    fun canRedo(): Boolean
}
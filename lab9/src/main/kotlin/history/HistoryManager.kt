package history

interface HistoryManager {
    fun runCommand(command: Command)
    fun undo()
    fun redo()
    fun canUndo(): Boolean
    fun canRedo(): Boolean

    fun doOnHistoryChanged(handler: () -> Unit)
}
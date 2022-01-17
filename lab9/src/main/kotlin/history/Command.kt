package history

interface Command {
    fun doAction()
    fun undoAction()
}
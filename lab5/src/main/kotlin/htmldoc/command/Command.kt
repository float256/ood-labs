package htmldoc.command

interface Command : Disposable {
    fun execute()
    fun undo()
    override fun dispose() {}
}

package htmldoc.command

import htmldoc.HtmlDoc

interface Command : Disposable {
    fun execute()
    fun undo()
    override fun dispose() {}
}

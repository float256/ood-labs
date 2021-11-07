package htmldoc.command

import htmldoc.HtmlDocContent

class SetTitle(
    private val newTitle: String,
    private val docContent: HtmlDocContent
): OneExecutableCommand() {
    private val prevTitle = docContent.title
    override fun executeImpl() {
        docContent.title = newTitle
    }

    override fun undoImpl() {
        docContent.title = prevTitle
    }
}
package htmldoc.command

import htmldoc.HtmlDocContent
import htmldoc.history.History
import htmldoc.item.ParagraphItem

class InsertParagraph(
    private val text: String,
    private val position: Int,
    private val docContent: HtmlDocContent,
    private val history: History
): OneExecutableCommand() {
    override fun executeImpl() {
        docContent.items.add(position, ParagraphItem(text, history))
    }

    override fun undoImpl() {
        docContent.items.removeAt(position)
    }
}
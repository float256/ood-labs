package htmldoc.item

import htmldoc.command.ReplaceText
import htmldoc.history.History
import htmldoc.item.state.ParagraphState

class ParagraphItem(
    text: String,
    private val commandHistory: History
) : HtmlDocItem {
    override val description = "Paragraph: $text"

    private var paragraphState = ParagraphState(text)
    val text: String
        get() = paragraphState.text

    fun changeText(text: String) {
        commandHistory.addAndExecuteCommand(ReplaceText(paragraphState, text))
    }
}
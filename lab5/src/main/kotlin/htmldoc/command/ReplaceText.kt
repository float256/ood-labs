package htmldoc.command

import htmldoc.item.state.ParagraphState

class ReplaceText(
    private val paragraphState: ParagraphState,
    private val newText: String,
): OneExecutableCommand() {
    private val prevText = paragraphState.text

    override fun executeImpl() {
        paragraphState.text = newText
    }

    override fun undoImpl() {
        paragraphState.text = prevText
    }
}
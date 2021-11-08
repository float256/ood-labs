package htmldoc.command

import htmldoc.item.state.ParagraphState
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestReplaceText {
    @Test
    fun `Execution and reverting for command works correctly`() {
        val paragraphState = ParagraphState("Old text")
        val command = ReplaceText(paragraphState, "New text")

        command.execute()
        assertEquals("New text", paragraphState.text)
        command.undo()
        assertEquals("Old text", paragraphState.text)
    }
}
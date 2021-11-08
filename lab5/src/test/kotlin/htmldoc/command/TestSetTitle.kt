package htmldoc.command

import htmldoc.HtmlDocContent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestSetTitle {
    @Test
    fun `Execution and reverting for command works correctly`() {
        val oldTitle = "Old title"
        val newTitle = "New Title"
        val content = HtmlDocContent(title = oldTitle)
        val command = SetTitle(newTitle, content)
        command.execute()
        assertEquals(newTitle, content.title)
        command.undo()
        assertEquals(oldTitle, content.title)
    }
}
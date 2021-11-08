package htmldoc.command

import htmldoc.HtmlDocContent
import htmldoc.history.HistoryImpl
import htmldoc.item.ParagraphItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestInsertParagraph {
    @Test
    fun `Execution and reverting for command works correctly`() {
        val content = HtmlDocContent()
        val history = HistoryImpl()
        val command = InsertParagraph("Text", 0, content, history)

        command.execute()
        Assertions.assertEquals(1, content.items.size)
        Assertions.assertTrue(content.items[0] is ParagraphItem)
        Assertions.assertEquals("Paragraph: Text", content.items[0].description)

        command.undo()
        Assertions.assertTrue(content.items.isEmpty())
    }
}
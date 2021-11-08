package htmldoc.command

import htmldoc.HtmlDocContent
import htmldoc.dummy.DummyFileManager
import htmldoc.dummy.DummyItem
import htmldoc.history.HistoryImpl
import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.item.ParagraphItem
import htmldoc.resource.FileCopyContainer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class TestInsertImage {
    @Test
    fun `Execution and reverting for command works correctly`() {
        val content = HtmlDocContent()
        val resourceDirectory = Path("/root")
        val fileManager = DummyFileManager()
        val copyContainer = FileCopyContainer(resourceDirectory, fileManager)
        val history = HistoryImpl()
        val command = InsertImage(123, 124, Path("/user/hello"), 0, content, copyContainer, history)

        command.execute()
        Assertions.assertEquals(1, content.items.size)
        Assertions.assertTrue(content.items[0] is ImageItem)
        Assertions.assertEquals("Image: 123 124 /user/hello", content.items[0].description)

        command.undo()
        Assertions.assertTrue(content.items.isEmpty())
    }
}
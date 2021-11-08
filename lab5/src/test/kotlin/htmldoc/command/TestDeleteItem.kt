package htmldoc.command

import htmldoc.HtmlDocContent
import htmldoc.dummy.DummyFileManager
import htmldoc.dummy.DummyItem
import htmldoc.history.HistoryImpl
import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.resource.FileCopyContainer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.test.assertEquals

class TestDeleteItem {
    @Test
    fun `Execution and reverting for command works correctly`() {
        val items = mutableListOf<HtmlDocItem>(DummyItem())
        val content = HtmlDocContent(items)
        val resourceDirectory = Path("/root")
        val fileManager = DummyFileManager()
        val copyContainer = FileCopyContainer(resourceDirectory, fileManager)
        val command = DeleteItem(0, content, copyContainer)

        command.execute()
        Assertions.assertEquals(listOf<HtmlDocItem>(), content.items)
        command.undo()
        Assertions.assertEquals(items, content.items)
    }

    @Test
    fun `Disposing undone command doesn't delete file`() {
        val filePath = Path("/123.png")
        val history = HistoryImpl()
        val items = mutableListOf<HtmlDocItem>(ImageItem(123, 123, filePath, history))
        val content = HtmlDocContent(items)
        val resourceDirectory = Path("/root")
        val fileManager = DummyFileManager()
        val copyContainer = FileCopyContainer(resourceDirectory, fileManager)
        val command = DeleteItem(0, content, copyContainer)

        copyContainer.copy(filePath)
        command.execute()
        command.undo()
        command.dispose()

        assertEquals(2, fileManager.allElements.size)
    }
}
package htmldoc

import htmldoc.command.DeleteItem
import htmldoc.command.InsertImage
import htmldoc.command.InsertParagraph
import htmldoc.command.SetTitle
import htmldoc.file_manager.FileManager
import htmldoc.history.History
import htmldoc.history.HistoryImpl
import htmldoc.htmlgenerator.HtmlGenerator
import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.item.ParagraphItem
import htmldoc.resource.ResourceCopyContainer
import java.nio.file.Path

class HtmlDocImpl(
    private val resourceCopyContainer: ResourceCopyContainer,
    private val htmlGenerator: HtmlGenerator,
    private val fileManager: FileManager
) : HtmlDoc {
    private val commandHistory: History = HistoryImpl()
    private val content: HtmlDocContent = HtmlDocContent()
    override var title: String
        set(newTitle) {
            val command = SetTitle(newTitle, content)
            commandHistory.addAndExecuteCommand(command)
        }
        get() {
            return content.title
        }

    override fun insertParagraph(text: String, position: Int): ParagraphItem {
        val command = InsertParagraph(text, position, content, commandHistory)
        commandHistory.addAndExecuteCommand(command)
        return content.items[position] as ParagraphItem
    }

    override fun insertImage(width: Int, height: Int, path: Path, position: Int): ImageItem {
        val command = InsertImage(width, height, path, position, content, resourceCopyContainer, commandHistory)
        commandHistory.addAndExecuteCommand(command)
        return content.items[position] as ImageItem
    }

    override fun getItem(position: Int): HtmlDocItem {
        return content.items[position]
    }

    override fun getNumberOfItems(): Int {
        return content.items.size
    }

    override fun deleteItem(position: Int) {
        commandHistory.addAndExecuteCommand(DeleteItem(position, content, resourceCopyContainer))
    }

    override fun canUndo(): Boolean {
        return commandHistory.canUndo()
    }

    override fun undo() {
        commandHistory.undo()
    }

    override fun canRedo(): Boolean {
        return commandHistory.canRedo()
    }

    override fun redo() {
        commandHistory.redo()
    }

    override fun save(path: Path) {
        fileManager.save(htmlGenerator.generate(content, resourceCopyContainer), path)
    }
}
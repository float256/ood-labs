package htmldoc

import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.item.ParagraphItem
import java.nio.file.Path

interface HtmlDoc {
    var title: String

    fun insertParagraph(text: String, position: Int = HtmlDocConstants.END_POSITION): ParagraphItem
    fun insertImage(width: Int, height: Int, path: Path, position: Int = HtmlDocConstants.END_POSITION): ImageItem
    fun getItem(position: Int): HtmlDocItem
    fun deleteItem(position: Int)

    fun canUndo(): Boolean
    fun undo()

    fun canRedo(): Boolean
    fun redo()

    fun getNumberOfItems(): Int
    fun save(path: Path)
}

package htmldoc.command

import htmldoc.HtmlDocConstants
import htmldoc.HtmlDocContent
import htmldoc.history.History
import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.resource.ResourceCopyContainer
import java.nio.file.Path

class InsertImage(
    private val width: Int,
    private val height: Int,
    private val path: Path,
    private val position: Int,
    private val docContent: HtmlDocContent,
    private val resourceCopyContainer: ResourceCopyContainer,
    private val history: History
) : OneExecutableCommand() {
    override fun executeImpl() {
        resourceCopyContainer.copy(path)
        docContent.items.add(position, ImageItem(width, height, path, history))
    }

    override fun undoImpl() {
        resourceCopyContainer.markCopyToRemove(path)
        docContent.items.removeAt(position)
    }

    override fun dispose() {
        if (!isExecuted()) {
            resourceCopyContainer.removeCopy(path)
        }
    }
}
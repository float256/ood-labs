package htmldoc.command

import htmldoc.HtmlDocContent
import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.resource.ResourceCopyContainer

class DeleteItem(
    private val position: Int,
    private val docContent: HtmlDocContent,
    private val copyContainer: ResourceCopyContainer,
): OneExecutableCommand() {
    private var deletedItem: HtmlDocItem = docContent.items[position]

    override fun executeImpl() {
        docContent.items.removeAt(position)
    }

    override fun undoImpl() {
        docContent.items.add(position, deletedItem)
    }

    override fun dispose() {
        if (isExecuted() && (deletedItem is ImageItem)) {
            copyContainer.removeCopy((deletedItem as ImageItem).path)
        }
    }
}
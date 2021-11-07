package htmldoc.command

import htmldoc.item.state.ImageState

class ResizeImage(
    private val imageState: ImageState,
    private val newWidth: Int,
    private val newHeight: Int
): OneExecutableCommand() {
    private var prevWidth = imageState.width
    private var prevHeight = imageState.height

    override fun executeImpl() {
        imageState.height = newHeight
        imageState.width = newWidth
    }

    override fun undoImpl() {
        imageState.height = prevHeight
        imageState.width = prevWidth
    }
}
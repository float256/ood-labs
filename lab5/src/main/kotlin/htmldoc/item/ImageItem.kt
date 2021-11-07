package htmldoc.item

import htmldoc.command.ResizeImage
import htmldoc.history.History
import htmldoc.item.state.ImageState
import java.nio.file.Path

class ImageItem(
    width: Int,
    height: Int,
    path: Path,
    private val commandHistory: History
) : HtmlDocItem {
    override val description: String
        get() = "Image: ${this.width} ${this.height} ${this.path}"

    private val imageState = ImageState(width, height, path)
    val width: Int
        get() = imageState.width
    val height: Int
        get() = imageState.height
    val path: Path
        get() = imageState.path

    fun resize(newWidth: Int, newHeight: Int) {
        commandHistory.addAndExecuteCommand(ResizeImage(imageState, newWidth, newHeight))
    }
}
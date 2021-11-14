import app.paintPictureOnCanvas
import app.paintPictureOnModernGraphicsRenderer

fun main(args: Array<String>) {
    println("Should we use new API (y)?")
    val input = readLine()!!
    if (input.lowercase() == "y") {
        paintPictureOnModernGraphicsRenderer()
    } else {
        paintPictureOnCanvas()
    }
}
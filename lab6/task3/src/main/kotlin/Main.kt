import app.paintPictureOnCanvas
import app.paintPictureOnModernGraphicsRenderer

fun main() {
    println("Should we use new API (y)?")
    val input = readLine()!!
    if (input.lowercase() == "y") {
        paintPictureOnModernGraphicsRenderer()
    } else {
        paintPictureOnCanvas()
    }
}
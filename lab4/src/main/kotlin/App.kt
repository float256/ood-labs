import canvas.ConsoleOutputCanvas
import designer.DesignerImpl
import painter.Painter
import shapefactory.ShapeFactoryImpl

fun main() {
    val shapeFactory = ShapeFactoryImpl()
    val designer = DesignerImpl(shapeFactory)
    val painter = Painter()
    val canvas = ConsoleOutputCanvas()

    println("Enter shapes for draft:")
    val draft = designer.createDraft(System.`in`)

    println("Result:")
    painter.drawPicture(draft, canvas)
}

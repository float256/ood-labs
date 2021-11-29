import slides.canvas.*
import slides.shape.Ellipse
import slides.shape.Frame
import slides.shape.Rectangle
import slides.shape.composite.CompositeShape
import slides.shape.composite.CompositeShapeImpl
import java.io.File
import java.io.PrintStream

fun main() {
    val outputFile = PrintStream(File("/home/user/Documents/123.svg"))
    val canvas: Canvas = SVGCanvas(PrintStream(outputFile))
    canvas.use {
        drawSampleSlide(canvas)
    }
}

private fun drawSampleSlide(canvas: Canvas) {
    val nestedCompositeShape: CompositeShape = CompositeShapeImpl()
    nestedCompositeShape.add(
        Rectangle(Point(300.0, 50.0), 250.0, 200.0,
            RGBAColorConstants.BLACK, StrokeParameters(RGBAColorConstants.GREEN, 3.3)),
        Rectangle(Point(275.0, 175.0), 250.0, 200.0,
            null, StrokeParameters(RGBAColorConstants.BLUE, 3.3)),
        Rectangle(Point(250.0, 300.0), 250.0, 200.0,
            RGBAColorConstants.WHITE, StrokeParameters(RGBAColorConstants.FULLY_TRANSPARENT, 3.3))
    )

    val compositeShape: CompositeShape = CompositeShapeImpl()
    compositeShape.add(
        Rectangle(Point(0.0, 0.0), 600.0, 750.0,
            RGBAColor(84u, 84u, 84u, 1.0), StrokeParameters(RGBAColorConstants.BLACK, 3.3)),
        Ellipse(Point(75.0, 100.0), 150.0, 200.0,
            RGBAColorConstants.RED, StrokeParameters(RGBAColorConstants.FULLY_TRANSPARENT, 3.3)),
        Ellipse(Point(75.0, 100.0), 140.0, 190.0,
            RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.BLACK, 1.0)),
        nestedCompositeShape
    )

    compositeShape.draw(canvas)
    compositeShape.setFrame(Frame(Point(1000.0, 100.0), 300.0, 250.0))
    compositeShape.draw(canvas)
}
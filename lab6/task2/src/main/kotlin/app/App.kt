package app

import adapter.ModernGraphicCanvasRenderer
import graphics_lib.Canvas
import graphics_lib.CanvasImpl
import modern_graphics_lib.ModernGraphicsRenderer
import shape_drawing_lib.CanvasPainter
import shape_drawing_lib.Point
import shape_drawing_lib.Rectangle
import shape_drawing_lib.Triangle

fun paintPicture(painter: CanvasPainter) {
    val triangle = Triangle(
        Point(10, 15),
        Point(100, 200),
        Point(150, 250),
    )
    val rectangle = Rectangle(Point(30, 40), 18, 24)
    painter.draw(triangle)
    painter.draw(rectangle)
}

fun paintPictureOnCanvas() {
    val simpleCanvas: Canvas = CanvasImpl(System.out)
    val painter = CanvasPainter(simpleCanvas)
    paintPicture(painter)
}

fun paintPictureOnModernGraphicsRenderer() {
    val adapter = ModernGraphicCanvasRenderer(System.out)
    val painter = CanvasPainter(adapter)
    adapter.use {
        paintPicture(painter)
    }
}

package adapter

import graphics_lib.Canvas
import modern_graphics_lib.ModernGraphicsRenderer
import modern_graphics_lib.Point
import java.io.PrintStream

class ModernGraphicCanvasRenderer(
    outputStream: PrintStream
): Canvas, ModernGraphicsRenderer(outputStream) {
    private var currentPosition: Point? = null

    init {
        super.beginDraw()
    }

    override fun moveTo(x: Int, y: Int) {
        currentPosition = Point(x, y)
    }

    override fun lineTo(x: Int, y: Int) {
        when (currentPosition) {
            null -> throw UnsupportedOperationException("Starting position wasn't specified")
            else -> {
                super.drawLine(currentPosition!!, Point(x, y))
                currentPosition = Point(x, y)
            }
        }
    }
}
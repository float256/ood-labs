package adapter

import graphics_lib.Canvas
import modern_graphics_lib.ModernGraphicsRenderer
import modern_graphics_lib.Point

class ModernGraphicCanvas(
    private val modernGraphicsRenderer: ModernGraphicsRenderer
): Canvas {
    private var currentPosition: Point? = null

    init {
        modernGraphicsRenderer.beginDraw()
    }

    override fun moveTo(x: Int, y: Int) {
        currentPosition = Point(x, y)
    }

    override fun lineTo(x: Int, y: Int) {
        when(currentPosition) {
            null -> throw UnsupportedOperationException("Starting position wasn't specified")
            else -> {
                modernGraphicsRenderer.drawLine(currentPosition!!, Point(x, y))
                currentPosition = Point(x, y)
            }
        }
    }

    fun endDraw() {
        modernGraphicsRenderer.endDraw()
    }
}
package shape_drawing_lib

import graphics_lib.Canvas

class CanvasPainter(
    private val canvas: Canvas
) {
    fun draw(drawable: CanvasDrawable) {
        drawable.draw(canvas)
    }
}
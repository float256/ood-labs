package shape_drawing_lib

import graphics_lib.Canvas

class Rectangle(
    private var leftTop: Point,
    private var width: Int,
    private var height: Int,
    ): CanvasDrawable {
    override fun draw(canvas: Canvas) {
        canvas.moveTo(leftTop.x, leftTop.y)
        canvas.lineTo(leftTop.x + width, leftTop.y)
        canvas.lineTo(leftTop.x + width, leftTop.y - height)
        canvas.lineTo(leftTop.x, leftTop.y - height)
        canvas.lineTo(leftTop.x, leftTop.y)
    }
}
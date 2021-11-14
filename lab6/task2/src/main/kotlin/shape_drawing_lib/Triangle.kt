package shape_drawing_lib

import graphics_lib.Canvas

class Triangle(
    private var p1: Point,
    private var p2: Point,
    private var p3: Point
) : CanvasDrawable {
    override fun draw(canvas: Canvas) {
        canvas.moveTo(p1.x, p1.y)
        canvas.lineTo(p2.x, p2.y)
        canvas.lineTo(p3.x, p3.y)
        canvas.lineTo(p1.x, p1.y)
    }
}
package shape

import canvas.Canvas

class Rectangle(
    override val color: Color,
    val leftTop: Point,
    val rightDown: Point,
) : Shape {

    init {
        if ((leftTop.x >= rightDown.x) || (leftTop.y <= rightDown.y)) {
            throw IllegalArgumentException()
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.color = color
        canvas.drawLine(leftTop, Point(rightDown.x, leftTop.y))
        canvas.drawLine(Point(rightDown.x, leftTop.y), rightDown)
        canvas.drawLine(rightDown, Point(leftTop.x, rightDown.y))
        canvas.drawLine(Point(leftTop.x, rightDown.y), leftTop)
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "Rectangle: color=$color, leftTop=$leftTop, rightDown=$rightDown"
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is Rectangle -> hashCode() == other.hashCode()
        else -> false
    }
}
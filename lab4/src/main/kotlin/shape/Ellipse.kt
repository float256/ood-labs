package shape

import canvas.Canvas

class Ellipse(
    override val color: Color,
    val center: Point,
    val width: Int,
    val height: Int
) : Shape {
    init {
        if ((width <= 0) || (height <= 0)) {
            throw IllegalArgumentException()
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.color = color
        canvas.drawEllipse(center, width, height)
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "Ellipse: color=$color, center=$center, width=$width, height=$height"
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is Ellipse -> hashCode() == other.hashCode()
        else -> false
    }
}
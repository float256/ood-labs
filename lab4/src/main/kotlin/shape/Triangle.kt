package shape

import canvas.Canvas

class Triangle(
    override val color: Color,
    val vertex1: Point,
    val vertex2: Point,
    val vertex3: Point
) : Shape {

    init {
        if ((vertex1 == vertex2) || (vertex2 == vertex3) || (vertex3 == vertex1)) {
            throw IllegalArgumentException()
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.color = color
        canvas.drawLine(vertex1, vertex2)
        canvas.drawLine(vertex2, vertex3)
        canvas.drawLine(vertex3, vertex1)
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "Triangle: color=$color, vertex1=$vertex1, vertex2=$vertex2, vertex3=$vertex3"
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is Triangle -> hashCode() == other.hashCode()
        else -> false
    }
}
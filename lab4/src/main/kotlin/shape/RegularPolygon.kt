package shape

import canvas.Canvas
import kotlin.math.*

class RegularPolygon(
    override val color: Color,
    val vertexCount: Int,
    val center: Point,
    val radius: Int
) : Shape {
    init {
        if ((vertexCount < 3) || (radius < 1)) {
            throw IllegalArgumentException()
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.color = color
        var previousPoint: Point? = null
        var firstPoint: Point? = null
        var currPoint: Point

        val angle = 2 * Math.PI / vertexCount
        for(currPointIdx in (0..vertexCount)) {
            currPoint = when(currPointIdx) {
                vertexCount -> firstPoint!!
                else -> Point(
                    (center.x + radius * sin(currPointIdx * angle)).toInt(),
                    (center.y + radius * cos(currPointIdx * angle)).toInt()
                )
            }
            firstPoint = firstPoint ?: currPoint
            previousPoint?.let {
                canvas.drawLine(it, currPoint)
            }
            previousPoint = currPoint
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "RegularPolygon: color=$color, vertexCount=$vertexCount, center=$center, radius=$radius"
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is RegularPolygon -> hashCode() == other.hashCode()
        else -> false
    }
}
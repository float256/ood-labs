package canvas

import shape.Color
import shape.Point

interface Canvas {
    var color: Color
    fun drawLine(from: Point, to: Point)
    fun drawEllipse(center: Point, width: Int, height: Int)
}
package canvas

import shape.Color
import shape.Point

class ConsoleOutputCanvas: Canvas {
    override var color: Color = Color.Black

    override fun drawLine(from: Point, to: Point) {
        println("Line: $from -> $to")
    }

    override fun drawEllipse(center: Point, width: Int, height: Int) {
        if ((width <= 0) || (height <= 0)) {
            throw IllegalArgumentException("Incorrect ellipse parameters")
        }
        println("Ellipse: center=$center, width=$width, height=$height")
    }
}
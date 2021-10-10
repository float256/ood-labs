package utils

import canvas.Canvas
import shape.Color
import shape.Point

class DummyCanvas : Canvas {
    override var color: Color = Color.Black
    val allAddedPrimitives: MutableList<String> = mutableListOf()

    override fun drawLine(from: Point, to: Point) {
        allAddedPrimitives.add("line: $color ${from.x} ${from.y} ${to.x} ${to.y}")
    }

    override fun drawEllipse(center: Point, width: Int, height: Int) {
        allAddedPrimitives.add("ellipse: $color ${center.x} ${center.y} $width $height")
    }
}
package slides.shape.composite.dummy

import slides.canvas.*

class DummyCanvas: Canvas {
    override var fillColor: RGBAColor? = RGBAColorConstants.FULLY_TRANSPARENT
    override var strokeParameters = StrokeParameters(RGBAColorConstants.WHITE, 1.0)

    var output = ""
        private set

    private var isClosed = false

    override fun drawLine(start: Point, end: Point) {
        assertIsClosed()
        output += "Line ($start), ($end), $fillColor, $strokeParameters"
    }

    override fun drawEllipse(center: Point, width: Double, height: Double) {
        assertIsClosed()
        output += "Line ($center), $width, $height, $fillColor, $strokeParameters"
    }

    override fun drawPolygon(points: List<Point>) {
        assertIsClosed()
        output += "Polygon (${points.joinToString(" ")}), $fillColor, $strokeParameters"
    }

    override fun close() {
        assertIsClosed()
        output += "Closed"
        isClosed = false
    }

    private fun assertIsClosed() {
        if (isClosed) {
            throw IllegalArgumentException("Closed")
        }
    }
}
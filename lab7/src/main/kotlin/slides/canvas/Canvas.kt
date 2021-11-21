package slides.canvas

interface Canvas: AutoCloseable {
    var fillColor: RGBAColor
    var strokeParameters: StrokeParameters

    fun drawLine(start: Point, end: Point)
    fun drawEllipse(center: Point, width: Double, height: Double)
    fun drawPolygon(points: List<Point>)
}
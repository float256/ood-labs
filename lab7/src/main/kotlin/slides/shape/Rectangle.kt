package slides.shape

import slides.canvas.Canvas
import slides.canvas.Point
import slides.canvas.RGBAColor
import slides.canvas.StrokeParameters

class Rectangle(
    private var leftTop: Point,
    private var width: Double,
    private var height: Double,
    fillColor: RGBAColor,
    strokeParameters: StrokeParameters
) : PrimitiveShape(fillColor, strokeParameters) {

    init {
        if ((width <= 0) || (height <= 0)) {
            throw IllegalArgumentException("Width and height can only be positive")
        }
    }

    override fun getFrame(): Frame {
        return Frame(leftTop, width, height)
    }

    override fun setFrame(newFrame: Frame) {
        leftTop = newFrame.leftTop
        width = newFrame.width
        height = newFrame.height
    }

    override fun draw(canvas: Canvas) {
        canvas.strokeParameters = getStroke()
        canvas.fillColor = getFillColor()
        canvas.drawPolygon(
            listOf(
                Point(leftTop.x, leftTop.y),
                Point(leftTop.x + width, leftTop.y),
                Point(leftTop.x + width, leftTop.y + height),
                Point(leftTop.x, leftTop.y + height),
                Point(leftTop.x, leftTop.y),
            )
        )
    }
}
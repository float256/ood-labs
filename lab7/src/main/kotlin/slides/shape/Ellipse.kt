package slides.shape

import slides.canvas.Canvas
import slides.canvas.Point
import slides.canvas.RGBAColor
import slides.canvas.StrokeParameters

class Ellipse(
    private var center: Point,
    private var width: Double,
    private var height: Double,
    fillColor: RGBAColor,
    strokeParameters: StrokeParameters
) : PrimitiveShape(fillColor, strokeParameters) {

    override fun getFrame(): Frame {
        return Frame(
            Point(center.x - width / 2, center.y - height / 2),
            width, height
        )
    }

    override fun setFrame(newFrame: Frame) {
        width = newFrame.width
        height = newFrame.height
        center = Point(newFrame.leftTop.x + width / 2, newFrame.leftTop.y + height / 2)
    }

    override fun draw(canvas: Canvas) {
        canvas.fillColor = getFillColor()
        canvas.strokeParameters = getStroke()
        canvas.drawEllipse(center, width, height)
    }
}
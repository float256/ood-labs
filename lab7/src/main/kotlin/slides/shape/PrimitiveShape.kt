package slides.shape

import slides.canvas.RGBAColor
import slides.canvas.StrokeParameters

abstract class PrimitiveShape(
    private var fillColor: RGBAColor?,
    private var strokeParameters: StrokeParameters
): Shape {
    override fun getFillColor(): RGBAColor? {
        return fillColor
    }

    override fun setFillColor(newColor: RGBAColor?) {
        fillColor = newColor
    }

    override fun getStroke(): StrokeParameters {
        return strokeParameters
    }

    override fun setStroke(newStroke: StrokeParameters) {
        strokeParameters = newStroke
    }
}
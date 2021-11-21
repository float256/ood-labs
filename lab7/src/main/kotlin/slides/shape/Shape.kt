package slides.shape

import slides.canvas.Canvas
import slides.canvas.RGBAColor
import slides.canvas.StrokeParameters

interface Shape {
    fun getFrame(): Frame?
    fun setFrame(newFrame: Frame)

    fun getFillColor(): RGBAColor?
    fun setFillColor(newColor: RGBAColor)

    fun getStroke(): StrokeParameters?
    fun setStroke(newStroke: StrokeParameters)

    fun draw(canvas: Canvas)
}
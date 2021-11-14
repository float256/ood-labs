package adapter

import graphics_lib.Canvas
import modern_graphics_lib.ModernGraphicsRenderer
import modern_graphics_lib.Point
import modern_graphics_lib.RGBAColor
import java.awt.Color

class ModernGraphicCanvas(
    private val modernGraphicsRenderer: ModernGraphicsRenderer
): Canvas {
    private var currentPosition: Point? = null
    private var currentColor: RGBAColor = RGBAColor(0.0, 0.0, 0.0, 1.0)

    init {
        modernGraphicsRenderer.beginDraw()
    }

    override fun moveTo(x: Int, y: Int) {
        currentPosition = Point(x, y)
    }

    override fun lineTo(x: Int, y: Int) {
        when(currentPosition) {
            null -> throw UnsupportedOperationException("Starting position wasn't specified")
            else -> {
                modernGraphicsRenderer.drawLine(currentPosition!!, Point(x, y), currentColor)
                currentPosition = Point(x, y)
            }
        }
    }

    override fun setColor(rgbColor: Int) {
        if (rgbColor in (0..0xffffff)) {
            val color = Color.decode(rgbColor.toString())
            currentColor = RGBAColor(
                color.red / 255.0,
                color.green / 255.0,
                color.blue / 255.0,
                1.0)
        } else {
            throw IllegalArgumentException("Incorrect RGB color value")
        }
    }
}
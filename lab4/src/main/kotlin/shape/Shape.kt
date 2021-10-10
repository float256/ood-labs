package shape

import canvas.Canvas

interface Shape {
    val color: Color
    fun draw(canvas: Canvas)
}
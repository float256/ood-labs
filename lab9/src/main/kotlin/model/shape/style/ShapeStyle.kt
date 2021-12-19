package model.shape.style

class ShapeStyle(
    val strokeWidth: Double,
    val strokeColor: RGBColor,
    val fillColor: RGBColor
) {
    init {
        if (strokeWidth <= 0.0) {
            throw IllegalArgumentException("Width and height can only be positive")
        }
    }
}
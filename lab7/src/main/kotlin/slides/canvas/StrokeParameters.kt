package slides.canvas

class StrokeParameters(
    var color: RGBAColor,
    width: Double
) {
    var width: Double = width
        set(value) {
            if (value > 0) {
                field = value
            } else {
                throw IllegalArgumentException("Width can only be positive")
            }
        }
}
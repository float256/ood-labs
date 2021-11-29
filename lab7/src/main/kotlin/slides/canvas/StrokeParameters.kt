package slides.canvas

class StrokeParameters(
    var color: RGBAColor?,
    width: Double?,
) {
    var width: Double? = width
        set(value) {
            if ((value == null) || (value > 0)) {
                field = value
            } else {
                throw IllegalArgumentException("Width can only be positive")
            }
        }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is StrokeParameters -> hashCode() == toString().hashCode()
            else -> false
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "StrokeParameters ($color, $width)"
    }
}
package slides.canvas

class RGBAColor(var red: UByte, var green: UByte, var blue: UByte, alpha: Double) {
    var alpha: Double = alpha
        set(value) {
            if ((value >= 0.0) && (value <= 0.0)) {
                field = value
            } else {
                throw IllegalArgumentException("Incorrect alpha channel value")
            }
        }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is RGBAColor -> other.hashCode() == hashCode()
            else -> false
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "rgba($red, $green, $blue, $alpha)"
    }
}


object RGBAColorConstants {
    val BLACK = RGBAColor(0u, 0u, 0u, 1.0)
    val WHITE = RGBAColor(255u, 255u, 255u, 1.0)
    val RED = RGBAColor(255u, 0u, 0u, 1.0)
    val GREEN = RGBAColor(0u, 255u, 0u, 1.0)
    val BLUE = RGBAColor(0u, 0u, 255u, 1.0)
    val FULLY_TRANSPARENT = RGBAColor(255u, 255u, 255u, 0.0)
}
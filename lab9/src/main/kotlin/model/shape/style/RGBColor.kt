package model.shape.style

data class RGBColor(
    var red: UByte,
    var green: UByte,
    var blue: UByte
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is RGBColor -> other.hashCode() == hashCode()
            else -> false
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "rgba($red, $green, $blue)"
    }
}
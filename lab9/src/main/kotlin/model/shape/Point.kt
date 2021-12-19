package model.shape

data class Point(
    val x: Double,
    val y: Double
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Point -> other.hashCode() == hashCode()
            else -> false
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "$x,$y"
    }
}

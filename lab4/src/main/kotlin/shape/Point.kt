package shape

data class Point(
    var x: Int,
    var y: Int
) {
    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Point -> other.hashCode() == hashCode()
            else -> false
        }
    }
}
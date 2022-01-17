package common

data class Size(
    val width: Float, val height: Float,
) {
    init {
        if ((width <= 0.0) || (height <= 0.0)) {
            throw IllegalArgumentException("Width and height can only be positive")
        }
    }
}
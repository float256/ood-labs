package model.shape.style

import model.shape.Point

data class Frame(val leftTop: Point, val width: Double, val height: Double) {
    init {
        if ((width <= 0.0) || (height <= 0.0)) {
            throw IllegalArgumentException("Width and height can only be positive")
        }
    }
}
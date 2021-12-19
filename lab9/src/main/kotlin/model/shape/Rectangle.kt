package model.shape

import model.shape.style.Frame
import model.shape.style.ShapeStyle

class Rectangle(
    leftTop: Point,
    width: Double,
    height: Double,
    style: ShapeStyle
): BaseShape(style) {
    var width = width
        private set
    var height = height
        private set
    var leftTop = leftTop
        private set

    init {
        if ((width <= 0) || (height <= 0)) {
            throw IllegalArgumentException("Width and height can only be positive")
        }
    }

    override var frame: Frame
        get() = Frame(leftTop, width, height)
        set(value) {
            width = value.width
            height = value.height
            leftTop = value.leftTop
            changeFrameObservableSubject.onNext(value)
        }
}
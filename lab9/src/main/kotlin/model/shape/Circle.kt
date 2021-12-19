package model.shape

import model.shape.style.Frame
import model.shape.style.ShapeStyle

class Circle(
    center: Point,
    width: Double,
    height: Double,
    style: ShapeStyle
): BaseShape(style) {
    var center = center
        private set
    var width = width
        private set
    var height = height
        private set

    override var frame: Frame
        get() = Frame(
            Point(
                center.x - width / 2,
                center.y - height / 2,
            ),
            width,
            height
        )
        set(value) {
            center = Point(
                value.leftTop.x + value.width / 2,
                value.leftTop.y + value.height / 2
            )
            width = value.width
            height = value.height
            changeFrameObservableSubject.onNext(value)
        }
}
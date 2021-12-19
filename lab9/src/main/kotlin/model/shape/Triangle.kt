package model.shape

import model.shape.style.Frame
import model.shape.style.ShapeStyle

class Triangle(
    topPoint: Point,
    width: Double,
    height: Double,
    style: ShapeStyle
) : BaseShape(style) {
    var topPoint: Point = topPoint
        private set
    var width: Double = width
        private set
    var height: Double = height
        private set

    override var frame: Frame
        get() = Frame(
            Point(
                topPoint.x - width / 2,
                topPoint.y
            ),
            width,
            height
        )
        set(value) {
            topPoint = Point(
                value.leftTop.x + value.width / 2,
                value.leftTop.y
            )
            width = value.width
            height = value.height
            changeFrameObservableSubject.onNext(value)
        }
}
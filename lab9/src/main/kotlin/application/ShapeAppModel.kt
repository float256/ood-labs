package application

import common.Frame
import domain.shape.Shape
import io.reactivex.rxjava3.disposables.Disposable

class ShapeAppModel(
    internal val domainModel: Shape
) {
    val id
        get() = domainModel.id
    val frame: Frame
        get() = domainModel.frame
    val type
        get() = domainModel.type

    fun doOnFrameChange(func: (Frame) -> Unit): Disposable {
        return domainModel.doOnFrameChange(func)
    }
}
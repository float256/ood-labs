package model.shape

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import model.shape.style.Frame
import model.shape.style.ShapeStyle

abstract class BaseShape(
    style: ShapeStyle
): Shape {
    protected var changeFrameObservableSubject: PublishSubject<Frame> = PublishSubject.create()
    private var changeStyleObservableSubject: PublishSubject<ShapeStyle> = PublishSubject.create()

    override fun doOnStyleChange(func: (ShapeStyle) -> Unit): Disposable {
        return changeStyleObservableSubject.subscribe(func)
    }

    override fun doOnFrameChange(func: (Frame) -> Unit): Disposable {
        return changeFrameObservableSubject.subscribe(func)
    }

    override var style: ShapeStyle = style
        set(value) {
            field = value
            changeStyleObservableSubject.onNext(value)
        }
}
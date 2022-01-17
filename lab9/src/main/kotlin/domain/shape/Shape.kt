package domain.shape

import common.Frame
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import kotlin.properties.Delegates

class Shape(
    frame: Frame,
    val type: ShapeType,
) {
    @Transient private var changeFrameObservableSubject: PublishSubject<Frame> = PublishSubject.create()

    val id = UUID.randomUUID().toString()
    var frame: Frame by Delegates.observable(frame) { _, _, new ->
        changeFrameObservableSubject.onNext(new)
    }

    fun doOnFrameChange(func: (Frame) -> Unit): Disposable {
        return changeFrameObservableSubject.subscribe(func)
    }
}
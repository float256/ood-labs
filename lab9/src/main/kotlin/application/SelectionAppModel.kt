package application

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

class SelectionAppModel {
    private var changeSelectionObservableSubject: PublishSubject<Optional<ShapeAppModel>> = PublishSubject.create()

    var selectedShape: ShapeAppModel? = null
        set(value) {
            field = value
            changeSelectionObservableSubject.onNext(Optional.ofNullable(value))
        }

    fun doOnSelectionChange(func: (newSelectedShape: ShapeAppModel?) -> Unit): Disposable {
        return changeSelectionObservableSubject.subscribe { optional ->
            if (optional.isPresent) {
                func(optional.get())
            } else {
                func(null)
            }
        }
    }
}
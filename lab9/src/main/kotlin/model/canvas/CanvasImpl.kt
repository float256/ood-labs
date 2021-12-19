package model.canvas

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import model.shape.Shape

class CanvasImpl: Canvas {
    private val allShapes: MutableList<Shape> = arrayListOf()
    private var addShapeObservableSubject: PublishSubject<Int> = PublishSubject.create()
    private var deleteShapeObservableSubject: PublishSubject<Pair<Int, Shape>> = PublishSubject.create()

    override val shapeCount: Int
        get() = allShapes.size

    override fun getShape(index: Int): Shape {
        return allShapes[index]
    }

    override fun insertShape(shape: Shape, index: Int) {
        allShapes[index] = shape
        addShapeObservableSubject.onNext(index)
    }

    override fun deleteShape(index: Int) {
        val deletedShape = allShapes[index]
        allShapes.removeAt(index)
        deleteShapeObservableSubject.onNext(Pair(index, deletedShape))
    }

    override fun doOnShapeAdded(observer: (Int) -> Unit): Disposable {
        return addShapeObservableSubject.subscribe(observer)
    }

    override fun doOnShapeDeleted(observer: (Int, Shape) -> Unit): Disposable {
        return deleteShapeObservableSubject.subscribe {
            observer(it.first, it.second)
        }
    }
}
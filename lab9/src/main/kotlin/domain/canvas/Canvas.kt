package domain.canvas

import domain.shape.Shape
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

class Canvas {
    private val mutableShapesList: MutableList<Shape> = arrayListOf()

    @Transient private var addShapeObservableSubject: PublishSubject<Pair<Int, Shape>> = PublishSubject.create()
    @Transient private var deleteShapeObservableSubject: PublishSubject<Pair<Int, Shape>> = PublishSubject.create()

    val shapeCount: Int
        get() = mutableShapesList.size
    val allShapes: List<Shape>
        get() = mutableShapesList

    fun getByShapeId(id: String): Shape {
        return mutableShapesList.first { it.id == id }
    }

    fun getByPosition(position: Int): Shape {
        return mutableShapesList[position]
    }

    fun addShape(shape: Shape, position: Int) {
        mutableShapesList.add(position, shape)
        addShapeObservableSubject.onNext(Pair(position, shape))
    }

    fun addShape(shape: Shape) {
        addShape(shape, shapeCount)
    }

    fun deleteShape(id: String) {
        mutableShapesList.removeIf { it.id == id }
    }

    fun deleteShape(position: Int) {
        val deletedShape = mutableShapesList[position]
        mutableShapesList.removeAt(position)
        deleteShapeObservableSubject.onNext(Pair(position, deletedShape))
    }

    fun doOnShapeAdded(observer: (Int, Shape) -> Unit): Disposable {
        return addShapeObservableSubject.subscribe{
            observer(it.first, it.second)
        }
    }

    fun doOnShapeDeleted(observer: (Int, Shape) -> Unit): Disposable {
        return deleteShapeObservableSubject.subscribe {
            observer(it.first, it.second)
        }
    }
}
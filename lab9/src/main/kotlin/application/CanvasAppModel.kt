package application

import domain.canvas.Canvas
import io.reactivex.rxjava3.disposables.Disposable

class CanvasAppModel(
    internal val domainModel: Canvas,
) {
    val shapeCount: Int
        get() = domainModel.shapeCount
    val allShapes: MutableList<ShapeAppModel>
        get() = domainModel.allShapes.map { ShapeAppModel(it) }.toMutableList()

    fun getByShapeId(id: String): ShapeAppModel {
        return ShapeAppModel(domainModel.getByShapeId(id))
    }

    fun doOnShapeAdded(observer: (Int, ShapeAppModel) -> Unit): Disposable {
        return domainModel.doOnShapeAdded { position, shape ->
            observer(position, ShapeAppModel(shape))
        }
    }

    fun doOnShapeDeleted(observer: (Int, ShapeAppModel) -> Unit): Disposable {
        return domainModel.doOnShapeDeleted { position, shape ->
            observer(position, ShapeAppModel(shape))
        }
    }
}
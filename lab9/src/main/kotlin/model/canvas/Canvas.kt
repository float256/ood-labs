package model.canvas

import io.reactivex.rxjava3.disposables.Disposable
import model.shape.Shape

interface Canvas {
    val shapeCount: Int
    fun getShape(index: Int): Shape
    fun insertShape(shape: Shape, index: Int)
    fun deleteShape(index: Int)

    fun doOnShapeAdded(observer: (Int) -> Unit): Disposable
    fun doOnShapeDeleted(observer: (Int, Shape) -> Unit): Disposable
}
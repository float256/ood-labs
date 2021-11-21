package slides.shape.composite

import slides.shape.Shape

interface CompositeShape: Shape {
    fun add(shape: Shape, position: Int)
    fun add(vararg shapes: Shape)
    fun remove(shape: Shape)
    fun getAllShapes(position: Int): List<Shape>
    fun isAdded(shape: Shape): Boolean
    fun isParent(shape: CompositeShape): Boolean
}
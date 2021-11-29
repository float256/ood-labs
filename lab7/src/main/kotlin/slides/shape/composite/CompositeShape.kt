package slides.shape.composite

import slides.shape.Shape

interface CompositeShape: Shape {
    fun add(shape: Shape, position: Int)
    fun add(vararg shapes: Shape)
    fun remove(position: Int)
    fun getAllShapes(): List<Shape>
    fun isChild(shape: Shape): Boolean
    fun isParent(shape: CompositeShape): Boolean
}
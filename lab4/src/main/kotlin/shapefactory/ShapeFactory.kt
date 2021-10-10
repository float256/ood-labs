package shapefactory

import shape.Shape

interface ShapeFactory {
    fun createShape(description: String): Shape
}
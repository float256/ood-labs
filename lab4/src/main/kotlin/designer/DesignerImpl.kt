package designer

import draft.PictureDraft
import shape.Shape
import shapefactory.ShapeFactory
import java.io.InputStream

class DesignerImpl(
    private val shapeFactory: ShapeFactory
): Designer {
    override fun createDraft(inputStream: InputStream): PictureDraft {
        val allShapes = mutableListOf<Shape>()
        inputStream.bufferedReader().forEachLine {
            allShapes.add(shapeFactory.createShape(it))
        }
        return PictureDraft(allShapes)
    }
}
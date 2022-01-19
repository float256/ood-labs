package application.usecase.create

import common.Frame
import common.Point
import common.Size
import domain.canvas.Canvas
import domain.shape.Shape
import domain.shape.ShapeType
import history.Command

class CreateShapeCommand(
    private val canvas: Canvas,
    private val position: Int,
    shapeType: ShapeType
): Command {
    val newShape = Shape(
        Frame(
            Point(ShapeDefaultParameters.LeftTopX, ShapeDefaultParameters.LeftTopY),
            Size(ShapeDefaultParameters.Width, ShapeDefaultParameters.Height)
        ),
        shapeType
    )

    override fun doAction() {
        canvas.addShape(newShape, position)
    }

    override fun undoAction() {
        canvas.deleteShape(position)
    }
}
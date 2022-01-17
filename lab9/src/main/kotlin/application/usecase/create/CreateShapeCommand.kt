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
    private val shapeType: ShapeType
): Command {
    override fun doAction() {
        val shape = Shape(
            Frame(
                Point(ShapeDefaultParameters.LeftTopX, ShapeDefaultParameters.LeftTopY),
                Size(ShapeDefaultParameters.Width, ShapeDefaultParameters.Height)
            ),
            shapeType
        )
        canvas.addShape(shape, position)
    }

    override fun undoAction() {
        canvas.deleteShape(position)
    }
}
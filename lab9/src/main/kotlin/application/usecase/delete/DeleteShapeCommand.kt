package application.usecase.delete

import domain.canvas.Canvas
import history.Command

class DeleteShapeCommand(
    private val canvas: Canvas,
    private val position: Int
): Command {
    private val removedShape = canvas.getByPosition(position)

    override fun doAction() {
        canvas.deleteShape(position)
    }

    override fun undoAction() {
        canvas.addShape(removedShape, position)
    }
}
package application.usecase.move

import common.Frame
import common.Point
import domain.shape.Shape
import history.Command

class MoveCommand(
    private val shape: Shape,
    private val newLeftTop: Point,
) : Command {
    private val oldLeftTop = shape.frame.leftTop

    override fun doAction() {
        shape.frame = Frame(newLeftTop, shape.frame.size)
    }

    override fun undoAction() {
        shape.frame = Frame(oldLeftTop, shape.frame.size)
    }
}
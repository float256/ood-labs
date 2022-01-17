package application.usecase.move

import application.ShapeAppModel
import common.Point
import history.HistoryManager

class MoveShapeUseCase(
    private val commandManager: HistoryManager,
    private val shapeAppModel: ShapeAppModel,
) {
    var newLeftTopX = shapeAppModel.frame.leftTop.x
    var newLeftTopY = shapeAppModel.frame.leftTop.y

    fun move(offsetX: Float, offsetY: Float) {
        newLeftTopX += offsetX
        newLeftTopY += offsetY
    }

    fun commit() {
        commandManager.runCommand(
            MoveCommand(
                shapeAppModel.domainModel,
                Point(newLeftTopX, newLeftTopY)
            )
        )
    }
}
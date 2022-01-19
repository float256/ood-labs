package application.usecase.move

import application.ShapeAppModel
import common.Point
import history.HistoryManager
import kotlin.math.max
import kotlin.math.min

class MoveShapeUseCase(
    private val canvasWidth: Float,
    private val canvasHeight: Float,
    private val commandManager: HistoryManager,
    private val shapeAppModel: ShapeAppModel,
) {
    var newLeftTopX = shapeAppModel.frame.leftTop.x
        private set
    var newLeftTopY = shapeAppModel.frame.leftTop.y
        private set

    private var mouseOffsetX = 0f
    private var mouseOffsetY = 0f

    fun move(offsetX: Float, offsetY: Float) {
        mouseOffsetX += offsetX
        mouseOffsetY += offsetY

        val initialLeftTop = shapeAppModel.frame.leftTop
        val initialSize = shapeAppModel.frame.size

        newLeftTopX = min(mouseOffsetX + initialLeftTop.x, canvasWidth - initialSize.width)
        newLeftTopX = max(0f, newLeftTopX)

        newLeftTopY = min(mouseOffsetY + initialLeftTop.y, canvasHeight - initialSize.height)
        newLeftTopY = max(0f, newLeftTopY)
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
package application.usecase.create

import application.CanvasAppModel
import domain.shape.ShapeType
import history.HistoryManager

class CreateShapeUseCase(
    private val canvasAppModel: CanvasAppModel,
    private val history: HistoryManager
) {
    fun createShape(shapeType: ShapeType, position: Int) {
        history.runCommand(CreateShapeCommand(canvasAppModel.domainModel, position, shapeType))
    }

    fun createShape(shapeType: ShapeType) {
        createShape(shapeType, canvasAppModel.shapeCount)
    }
}
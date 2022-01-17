package application.usecase.delete

import application.CanvasAppModel
import application.SelectionAppModel
import history.HistoryManager

class DeleteSelectedShapeUseCase(
    private val canvasAppModel: CanvasAppModel,
    private val selectionAppModel: SelectionAppModel,
    private val history: HistoryManager,
) {
    fun deleteSelectedShape() {
        if (selectionAppModel.selectedShape != null) {
            val shapeForRemoving = selectionAppModel.selectedShape!!
            selectionAppModel.selectedShape = null

            val position = canvasAppModel.domainModel.allShapes
                .indexOfFirst{ it.id == shapeForRemoving.id }
            history.runCommand(DeleteShapeCommand(canvasAppModel.domainModel, position))
        }
    }
}
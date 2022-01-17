package application.usecase.delete

import application.CanvasAppModel
import application.SelectionAppModel
import history.HistoryManager

class DeleteShapeUseCase(
    private val canvasAppModel: CanvasAppModel,
    private val selectionAppModel: SelectionAppModel,
    private val history: HistoryManager,
) {
    fun deleteSelectedShape() {
        if (selectionAppModel.selectedShape != null) {
            val position = canvasAppModel.domainModel.allShapes
                .indexOfFirst{ it.id == selectionAppModel.selectedShape!!.id }
            history.runCommand(DeleteShapeCommand(canvasAppModel.domainModel, position))
        }
    }
}
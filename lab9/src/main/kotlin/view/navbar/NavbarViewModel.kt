package view.navbar

import androidx.compose.runtime.mutableStateOf
import application.SelectionAppModel
import application.usecase.create.CreateShapeUseCase
import application.usecase.delete.DeleteShapeUseCase
import application.usecase.undo.UndoUseCase
import domain.shape.ShapeType

class NavbarViewModel(
    private val createShapeUseCase: CreateShapeUseCase,
    private val deleteShapeUseCase: DeleteShapeUseCase,
    private val undoUseCase: UndoUseCase,
    private val selectionAppModel: SelectionAppModel,
) {
    private val isUndoButtonActive = mutableStateOf(undoUseCase.canUndo())
    private val isRedoButtonActive = mutableStateOf(undoUseCase.canRedo())

    init {
        undoUseCase.doOnHistoryChanged {
            isUndoButtonActive.value = undoUseCase.canUndo()
            isRedoButtonActive.value = undoUseCase.canRedo()
        }
    }

    fun onCreateButtonClick(shapeType: ShapeType) {
        createShapeUseCase.createShape(shapeType)
        selectionAppModel.selectedShape = null
    }

    fun onDeleteButtonClick() {
        deleteShapeUseCase.deleteSelectedShape()
    }

    fun canUndo() = isUndoButtonActive.value
    fun canRedo() = isRedoButtonActive.value
    fun onUndoButtonClick() = undoUseCase.undo()
    fun onRedoButtonClick() = undoUseCase.redo()
}
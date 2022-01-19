package view.navbar

import androidx.compose.runtime.mutableStateOf
import application.SelectionAppModel
import application.usecase.create.CreateShapeUseCase
import application.usecase.delete.DeleteSelectedShapeUseCase
import application.usecase.undo.UndoUseCase
import domain.shape.ShapeType

class NavbarViewModel(
    private val createShapeUseCase: CreateShapeUseCase,
    private val deleteSelectedShapeUseCase: DeleteSelectedShapeUseCase,
    private val undoUseCase: UndoUseCase,
    private val selectionAppModel: SelectionAppModel,
) {
    private val isUndoButtonActive = mutableStateOf(undoUseCase.canUndo())
    private val isRedoButtonActive = mutableStateOf(undoUseCase.canRedo())
    private val isDeleteButtonActive = mutableStateOf(selectionAppModel.selectedShape != null)

    init {
        undoUseCase.doOnHistoryChanged {
            isUndoButtonActive.value = undoUseCase.canUndo()
            isRedoButtonActive.value = undoUseCase.canRedo()
            selectionAppModel.selectedShape = null
        }
        selectionAppModel.doOnSelectionChange { newSelectedShape ->
            isDeleteButtonActive.value = (newSelectedShape != null)
        }
    }

    fun onCreateButtonClick(shapeType: ShapeType) {
        createShapeUseCase.createShape(shapeType)
        selectionAppModel.selectedShape = null
    }

    fun onDeleteButtonClick() {
        deleteSelectedShapeUseCase.deleteSelectedShape()
    }

    fun canDelete(): Boolean {
        return isDeleteButtonActive.value
    }

    fun canUndo(): Boolean {
        return isUndoButtonActive.value
    }

    fun canRedo(): Boolean {
        return isRedoButtonActive.value
    }

    fun onUndoButtonClick() {
        undoUseCase.undo()
    }

    fun onRedoButtonClick() {
        undoUseCase.redo()
    }
}
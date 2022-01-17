package view.canvas

import androidx.compose.runtime.mutableStateListOf
import application.CanvasAppModel
import application.SelectionAppModel
import application.ShapeAppModel
import application.usecase.move.MoveShapeUseCaseFactory
import view.shape.ShapeViewModel

class CanvasViewModel(
    private val canvas: CanvasAppModel,
    private val selectionAppModel: SelectionAppModel,
    private val moveShapeUseCaseFactory: MoveShapeUseCaseFactory
) {
    val state = mutableStateListOf(*canvas.allShapes.map { it.id }.toTypedArray())
    var viewModelsPerEachShape = mutableMapOf<String, ShapeViewModel>()
        private set

    init {
        canvas.doOnShapeAdded { position, shape ->
            state.add(position, shape.id)
        }
        canvas.doOnShapeDeleted { position, _ ->
            state.removeAt(position)
        }
    }

    fun onClick() {
        selectionAppModel.selectedShape = null
    }

    fun getById(id: String): ShapeAppModel {
        return canvas.getByShapeId(id)
    }

    fun getShapeViewModel(id: String): ShapeViewModel {
        if (viewModelsPerEachShape[id] == null) {
            viewModelsPerEachShape[id] = ShapeViewModel(getById(id), selectionAppModel, moveShapeUseCaseFactory)
        }
        return viewModelsPerEachShape[id]!!
    }
}
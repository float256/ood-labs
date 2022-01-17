package view.canvas

import androidx.compose.runtime.mutableStateListOf
import application.CanvasAppModel
import application.SelectionAppModel
import application.ShapeAppModel

class CanvasViewModel(
    private val canvas: CanvasAppModel,
    private val selectionAppModel: SelectionAppModel,
) {
    val state = mutableStateListOf(*canvas.allShapes.toTypedArray())

    init {
        canvas.doOnShapeAdded { position, shape ->
            state.toMutableList()
            state.add(position, shape)
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
}
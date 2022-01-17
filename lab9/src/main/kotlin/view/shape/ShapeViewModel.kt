package view.shape

import androidx.compose.runtime.mutableStateOf
import application.SelectionAppModel
import application.ShapeAppModel
import application.usecase.move.MoveShapeUseCase
import application.usecase.move.MoveShapeUseCaseFactory
import common.Frame
import common.Point

class ShapeViewModel(
    private val shape: ShapeAppModel,
    private val selectionAppModel: SelectionAppModel,
    private val moveShapeUseCaseFactory: MoveShapeUseCaseFactory,
) {
    var state = mutableStateOf(shape.frame)
    val type = shape.type

    private var useCase: MoveShapeUseCase? = null

    init {
        shape.doOnFrameChange { frame ->
            state.value = frame
        }
    }

    fun onDragEnd() {
        useCase?.commit()
        selectionAppModel.selectedShape = shape
    }

    fun onDragStart() {
        useCase = useCase ?: moveShapeUseCaseFactory.create(shape)
        selectionAppModel.selectedShape = null
    }

    fun onDrag(offsetX: Float, offsetY: Float) {
        val prevFrame = state.value
        state.value = Frame(
            Point(
                prevFrame.leftTop.x + offsetX,
                prevFrame.leftTop.y + offsetY
            ),
            prevFrame.size
        )
        useCase?.move(offsetX, offsetY)
    }

    fun onClick() {
        selectionAppModel.selectedShape = shape
    }
}
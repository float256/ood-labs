package application.usecase.move

import application.ShapeAppModel
import history.HistoryManager

class MoveShapeUseCaseFactory(
    val windowWidth: Float,
    val windowHeight: Float,
    val history: HistoryManager
) {
    fun create(shape: ShapeAppModel): MoveShapeUseCase {
        return MoveShapeUseCase(windowWidth, windowHeight, history, shape)
    }
}
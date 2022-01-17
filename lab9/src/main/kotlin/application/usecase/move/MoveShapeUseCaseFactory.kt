package application.usecase.move

import application.ShapeAppModel
import history.HistoryManager

class MoveShapeUseCaseFactory(
    val history: HistoryManager
) {
    fun create(shape: ShapeAppModel): MoveShapeUseCase {
        return MoveShapeUseCase(history, shape)
    }
}
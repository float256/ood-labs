package view.canvas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import application.usecase.move.MoveShapeUseCaseFactory
import view.shape.ShapeView
import view.shape.ShapeViewModel

@Composable
fun CanvasView(
    viewModel: CanvasViewModel,
    moveShapeUseCaseFactory: MoveShapeUseCaseFactory
) {
    Box(Modifier.fillMaxSize()) {
        viewModel.state.forEach {
            ShapeView(ShapeViewModel(it, moveShapeUseCaseFactory))
        }
    }
}
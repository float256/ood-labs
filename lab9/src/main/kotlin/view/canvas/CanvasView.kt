package view.canvas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import application.SelectionAppModel
import application.usecase.move.MoveShapeUseCaseFactory
import view.shape.ShapeView
import view.shape.ShapeViewModel

@Composable
fun CanvasView(
    viewModel: CanvasViewModel,
) {
    viewModel.state.forEach { shapeId ->
        ShapeView(viewModel.getShapeViewModel(shapeId))
    }
}
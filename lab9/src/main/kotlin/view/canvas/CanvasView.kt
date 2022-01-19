package view.canvas

import androidx.compose.runtime.Composable
import view.shape.shapeView

@Composable
fun canvasView(
    viewModel: CanvasViewModel,
) {
    viewModel.state.forEach { shapeId ->
        shapeView(viewModel.getShapeViewModel(shapeId))
    }
}
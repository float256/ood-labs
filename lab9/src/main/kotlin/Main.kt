import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import application.CanvasAppModel
import application.SelectionAppModel
import application.usecase.create.CreateShapeUseCase
import application.usecase.delete.DeleteSelectedShapeUseCase
import application.usecase.move.MoveShapeUseCaseFactory
import application.usecase.undo.UndoUseCase
import domain.canvas.Canvas
import history.History
import view.canvas.CanvasView
import view.canvas.CanvasViewModel
import view.navbar.Navbar
import view.navbar.NavbarViewModel

@Composable
@Preview
fun app() {
    val canvas = Canvas()
    val history = History()
    val canvasAppModel = CanvasAppModel(canvas)
    val selectionAppModel = SelectionAppModel()

    val moveShapeUseCaseFactory = MoveShapeUseCaseFactory(history)
    val createShapeUseCase = CreateShapeUseCase(canvasAppModel, history)
    val deleteShapeUseCase = DeleteSelectedShapeUseCase(canvasAppModel, selectionAppModel, history)
    val undoUseCase = UndoUseCase(history)

    val canvasViewModel = CanvasViewModel(canvasAppModel, selectionAppModel, moveShapeUseCaseFactory)
    val navbarViewModel = NavbarViewModel(createShapeUseCase, deleteShapeUseCase, undoUseCase, selectionAppModel)

    DesktopMaterialTheme {
        Scaffold(
            bottomBar = {
                Navbar(navbarViewModel)
            }
        ) {
            CanvasView(canvasViewModel)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app()
    }
}

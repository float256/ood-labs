import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import application.CanvasAppModel
import application.SelectionAppModel
import application.usecase.create.CreateShapeUseCase
import application.usecase.delete.DeleteSelectedShapeUseCase
import application.usecase.move.MoveShapeUseCaseFactory
import application.usecase.undo.UndoUseCase
import domain.canvas.Canvas
import history.History
import view.canvas.canvasView
import view.canvas.CanvasViewModel
import view.navbar.navbar
import view.navbar.NavbarViewModel
import view.selection.SelectionViewModel
import view.selection.selection

@Composable
@Preview
fun app() {
    val canvas = Canvas()
    val history = History()
    val canvasAppModel = CanvasAppModel(canvas)
    val selectionAppModel = SelectionAppModel()

    val moveShapeUseCaseFactory = MoveShapeUseCaseFactory(
        WindowConfig.CanvasWidth, WindowConfig.CanvasHeight, history)
    val createShapeUseCase = CreateShapeUseCase(canvasAppModel, history)
    val deleteShapeUseCase = DeleteSelectedShapeUseCase(canvasAppModel, selectionAppModel, history)
    val undoUseCase = UndoUseCase(history)

    val canvasViewModel = CanvasViewModel(canvasAppModel, selectionAppModel, moveShapeUseCaseFactory)
    val navbarViewModel = NavbarViewModel(createShapeUseCase, deleteShapeUseCase, undoUseCase, selectionAppModel)
    val selectionViewModel = SelectionViewModel(selectionAppModel)

    DesktopMaterialTheme {
        Scaffold(
            bottomBar = {
                navbar(WindowConfig.BottomBarHeight.dp, navbarViewModel)
            }
        ) {
            canvasView(canvasViewModel)
            selection(selectionViewModel)
        }
    }
}

fun main() = application {
    val state = rememberWindowState(
        width = WindowConfig.CanvasWidth.dp,
        height = WindowConfig.CanvasHeight.dp + WindowConfig.BottomBarHeight.dp
    )
    Window(onCloseRequest = ::exitApplication,
        state = state,
        title = WindowConfig.Title,
        resizable = false
    ) {
        app()
    }
}

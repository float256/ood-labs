package view.navbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import domain.shape.ShapeType

@Composable
fun Navbar(navbarViewModel: NavbarViewModel) {
    BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
        NavbarItem(this, FeatherIcons.Triangle, "Create Triangle") {
            navbarViewModel.onCreateButtonClick(ShapeType.Triangle)
        }
        NavbarItem(this, FeatherIcons.Circle, "Create Ellipse") {
            navbarViewModel.onCreateButtonClick(ShapeType.Ellipse)
        }
        NavbarItem(this, FeatherIcons.Square, "Create Rectangle", ) {
            navbarViewModel.onCreateButtonClick(ShapeType.Rectangle)
        }
        NavbarItem(this, FeatherIcons.Delete, "Delete Shape") {
            navbarViewModel.onDeleteButtonClick()
        }
        NavbarItem(this, FeatherIcons.ArrowLeftCircle, "Undo", navbarViewModel.canUndo()) {
            navbarViewModel.onUndoButtonClick()
        }
        NavbarItem(this, FeatherIcons.ArrowRightCircle, "Redo", navbarViewModel.canRedo()) {
            navbarViewModel.onRedoButtonClick()
        }
    }
}
package view.navbar

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import domain.shape.ShapeType

@Composable
fun navbar(height: Dp, navbarViewModel: NavbarViewModel) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.height(height),
        elevation = height
    ) {
        navbarItem(this, FeatherIcons.Triangle, "Create Triangle") {
            navbarViewModel.onCreateButtonClick(ShapeType.Triangle)
        }
        navbarItem(this, FeatherIcons.Circle, "Create Ellipse") {
            navbarViewModel.onCreateButtonClick(ShapeType.Ellipse)
        }
        navbarItem(this, FeatherIcons.Square, "Create Rectangle", ) {
            navbarViewModel.onCreateButtonClick(ShapeType.Rectangle)
        }
        navbarItem(this, FeatherIcons.Delete, "Delete Shape") {
            navbarViewModel.onDeleteButtonClick()
        }
        navbarItem(this, FeatherIcons.ArrowLeftCircle, "Undo", navbarViewModel.canUndo()) {
            navbarViewModel.onUndoButtonClick()
        }
        navbarItem(this, FeatherIcons.ArrowRightCircle, "Redo", navbarViewModel.canRedo()) {
            navbarViewModel.onRedoButtonClick()
        }
    }
}
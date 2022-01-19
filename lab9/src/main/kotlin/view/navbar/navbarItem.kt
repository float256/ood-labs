package view.navbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun navbarItem(rowScope: RowScope, icon: ImageVector, label: String, isActive: Boolean = true, onClick: () -> Unit) {
    rowScope.BottomNavigationItem(
        icon = { Icon(icon, "") },
        label = { Text(label) },
        onClick = onClick,
        selected = false,
        enabled = isActive
    )
}
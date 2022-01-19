package view.selection

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun selection(selectionViewModel: SelectionViewModel) {
    if (selectionViewModel.isShowSelection()) {
        val frame = selectionViewModel.frame.value!!
        val size = frame.size
        val offset = frame.leftTop
        Box(
            modifier = Modifier
                .size(
                    width = size.width.dp,
                    height = size.height.dp
                )
                .offset(
                    x = offset.x.dp,
                    y = offset.y.dp
                )
                .clip(RectangleShape)
                .background(Color.Transparent)
        ) {
            val stroke = Stroke(width = 5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 5f)
            )
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(color = Color.LightGray, style = stroke)
            }
        }
    }
}
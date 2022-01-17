package view.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import domain.shape.ShapeType

@Composable
fun ShapeView(shapeViewModel: ShapeViewModel) {
    val size = shapeViewModel.state.value.size
    val offset = shapeViewModel.state.value.leftTop
    val shape = when (shapeViewModel.type) {
        ShapeType.Ellipse -> EllipseShape()
        else -> throw IllegalArgumentException()
    }

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
            .clip(shape)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { shapeViewModel.onDragStart() },
                    onDragEnd = { shapeViewModel.onDragEnd() },
                    onDrag = { change, dragAmount ->
                        change.consumeAllChanges()
                        shapeViewModel.onDrag(dragAmount.x, dragAmount.y)
                    }
                )
            }.clickable(
            ) {

            }
            .background(Color.White)
            .border(2.dp, Color.Gray, CircleShape)
    )
}
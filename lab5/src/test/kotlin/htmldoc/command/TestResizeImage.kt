package htmldoc.command

import htmldoc.item.state.ImageState
import htmldoc.item.state.ParagraphState
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.test.assertEquals

class TestResizeImage {
    @Test
    fun `Execution and reverting for command works correctly`() {
        val path = Path("/123.png")

        val imageState = ImageState(123, 123, path)
        val command = ResizeImage(imageState, 124, 125)

        command.execute()
        assertEquals(124, imageState.width)
        assertEquals(125, imageState.height)

        command.undo()
        assertEquals(123, imageState.width)
        assertEquals(123, imageState.height)
    }
}
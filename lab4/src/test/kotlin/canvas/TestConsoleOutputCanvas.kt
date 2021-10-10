package canvas

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shape.Point

class TestConsoleOutputCanvas {
    @Test
    fun `Attempting to draw ellipse with incorrect parameters throws exception`() {
        val canvas = ConsoleOutputCanvas()
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), 0, 0) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), 0, 1) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), 1, 0) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), 0, -1) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), -1, 0) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), -1, -1) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(2, 3), -2, -2) }
    }
}
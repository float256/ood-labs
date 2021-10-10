package shape

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import utils.DummyCanvas

class TestEllipse {
    @Test
    fun `Creation of object with incorrect parameters throws exception`() {
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), 0, 0) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), 0, 1) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), 1, 0) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), 1, -1) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), -1, 1) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), -1, -1) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.Black, Point(0, 0), -30, -40) }
    }

    @Test
    fun `Boundary case with correct parameters`() {
        val ellipse = Ellipse(Color.Red, Point(1, -1), 1, 1)
        val dummyCanvas = DummyCanvas()

        ellipse.draw(dummyCanvas)

        val expectedCommands = listOf("ellipse: Red 1 -1 1 1")
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }

    @Test
    fun `Non-boundary case with correct parameters`() {
        val ellipse = Ellipse(Color.Black, Point(0, 0), 10, 20)
        val dummyCanvas = DummyCanvas()

        ellipse.draw(dummyCanvas)

        val expectedCommands = listOf("ellipse: Black 0 0 10 20")
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }
}
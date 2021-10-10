package shape

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import utils.DummyCanvas

class TestRectangle {
    @Test
    fun `Creation of object with incorrect parameters throws exception`() {
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(1, 0)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(1, 1)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(0, 1)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(-1, 1)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(-1, 0)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(-1, -1)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.Black, Point(0, 0), Point(0, -1)) }
    }

    @Test
    fun `Boundary case with correct parameters`() {
        val rectangle = Rectangle(Color.Black, Point(2, 2), Point(3, 1))
        val dummyCanvas = DummyCanvas()

        rectangle.draw(dummyCanvas)

        val expectedCommands = listOf(
            "line: Black 2 2 3 2",
            "line: Black 3 2 3 1",
            "line: Black 3 1 2 1",
            "line: Black 2 1 2 2",
        )
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }

    @Test
    fun `Non-boundary case with correct parameters`() {
        val rectangle = Rectangle(Color.Black, Point(10, 5), Point(22, 4))
        val dummyCanvas = DummyCanvas()

        rectangle.draw(dummyCanvas)

        val expectedCommands = listOf(
            "line: Black 10 5 22 5",
            "line: Black 22 5 22 4",
            "line: Black 22 4 10 4",
            "line: Black 10 4 10 5",
        )
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }
}
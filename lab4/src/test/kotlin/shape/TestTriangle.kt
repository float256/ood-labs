package shape

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import utils.DummyCanvas

class TestTriangle {
    @Test
    fun `Creation of object with incorrect parameters throws exception`() {
        assertThrows<IllegalArgumentException> { Triangle(Color.Black, Point(0, 0), Point(1, 1), Point(1, 1)) }
        assertThrows<IllegalArgumentException> { Triangle(Color.Black, Point(1, 1), Point(0, 0), Point(1, 1)) }
        assertThrows<IllegalArgumentException> { Triangle(Color.Black, Point(1, 1), Point(1, 1), Point(0, 0)) }
    }

    @Test
    fun `Boundary case with correct parameters`() {
        val rectangle = Triangle(Color.Yellow, Point(0, 0), Point(0, 1), Point(1, 0))
        val dummyCanvas = DummyCanvas()

        rectangle.draw(dummyCanvas)

        val expectedCommands = listOf(
            "line: Yellow 0 0 0 1",
            "line: Yellow 0 1 1 0",
            "line: Yellow 1 0 0 0",
        )
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }

    @Test
    fun `Non-boundary case with correct parameters`() {
        val rectangle = Triangle(Color.Yellow, Point(10, 230), Point(50, -41), Point(21, -10))
        val dummyCanvas = DummyCanvas()

        rectangle.draw(dummyCanvas)

        val expectedCommands = listOf(
            "line: Yellow 10 230 50 -41",
            "line: Yellow 50 -41 21 -10",
            "line: Yellow 21 -10 10 230",
        )
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }
}
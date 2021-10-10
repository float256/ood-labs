package shape

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import utils.DummyCanvas

class TestRegularPolygon {
    @Test
    fun `Creation of object with incorrect parameters throws exception`() {
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.Red, -1, Point(15, 20), 200) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.Red, 0, Point(15, 20), 200) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.Red, 2, Point(15, 20), 200) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.Red, 3, Point(15, 20), -1) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.Red, 3, Point(15, 20), 0) }
    }

    @Test
    fun `Boundary case with correct parameters`() {
        val rectangle = RegularPolygon(Color.Red, 4, Point(0, 0), 1)
        val dummyCanvas = DummyCanvas()

        rectangle.draw(dummyCanvas)

        val expectedCommands = listOf(
            "line: Red 0 1 1 0",
            "line: Red 1 0 0 -1",
            "line: Red 0 -1 -1 0",
            "line: Red -1 0 0 1"
        )
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }

    @Test
    fun `Non-boundary case with correct parameters`() {
        val rectangle = RegularPolygon(Color.Red, 7, Point(15, 20), 200)
        val dummyCanvas = DummyCanvas()

        rectangle.draw(dummyCanvas)

        val expectedCommands = listOf(
            "line: Red 15 220 171 144",
            "line: Red 171 144 209 -24",
            "line: Red 209 -24 101 -160",
            "line: Red 101 -160 -71 -160",
            "line: Red -71 -160 -179 -24",
            "line: Red -179 -24 -141 144",
            "line: Red -141 144 15 220"
        )
        val actualCommands = dummyCanvas.allAddedPrimitives
        Assertions.assertEquals(expectedCommands, actualCommands)
    }
}
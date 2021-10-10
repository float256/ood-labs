package painter

import draft.PictureDraft
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import shape.*
import utils.DummyCanvas

class TestPainter {
    @Test
    fun `Test drawing draft on canvas`() {
        val painter = Painter()
        val draft = PictureDraft(
            listOf(
                Ellipse(Color.Pink, Point(3, 2), 10, 15),
                Rectangle(Color.Green, Point(2, 10), Point(3, 9)),
                RegularPolygon(Color.Blue, 4, Point(10, 10), 20),
                Triangle(Color.Yellow, Point(1, 1), Point(2, 2), Point(4, 6))
            )
        )
        val canvas = DummyCanvas()

        painter.drawPicture(draft, canvas)
        val actualOutput = canvas.allAddedPrimitives
        val expectedOutput = listOf(
            "ellipse: Pink 3 2 10 15",
            "line: Green 2 10 3 10",
            "line: Green 3 10 3 9",
            "line: Green 3 9 2 9",
            "line: Green 2 9 2 10",
            "line: Blue 10 30 30 10",
            "line: Blue 30 10 10 -10",
            "line: Blue 10 -10 -10 9",
            "line: Blue -10 9 10 30",
            "line: Yellow 1 1 2 2",
            "line: Yellow 2 2 4 6",
            "line: Yellow 4 6 1 1"
        )

        Assertions.assertEquals(expectedOutput, actualOutput)
    }
}
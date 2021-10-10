package designer

import draft.PictureDraft
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import shape.*
import shapefactory.ShapeFactoryImpl

class TestDesignerImpl {
    @Test
    fun `Creation of draft works correctly`() {
        val shapeFactory = ShapeFactoryImpl()
        val designer = DesignerImpl(shapeFactory)
        val inputStream = """
            Ellipse Green 23 -3 20 10
            Rectangle Red 0 100 153 -1
            RegularPolygon Pink 10 322 1337 20
            Triangle Green 1 1 2 3 4 5
        """.trimIndent().byteInputStream()

        val actualDraft = designer.createDraft(inputStream)
        val expectedDraft = PictureDraft(
            listOf(
                Ellipse(Color.Green, Point(23, -3), 20, 10),
                Rectangle(Color.Red, Point(0, 100), Point(153, -1)),
                RegularPolygon(Color.Pink, 10, Point(322, 1337), 20),
                Triangle(Color.Green, Point(1, 1), Point(2, 3), Point(4, 5)),
            )
        )

        Assertions.assertEquals(expectedDraft.shapes, actualDraft.shapes)
    }
}
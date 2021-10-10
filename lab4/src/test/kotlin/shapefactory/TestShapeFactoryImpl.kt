package shapefactory

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shape.*

class TestShapeFactoryImpl {
    @Test
    fun `Attempting to create a form using an incorrect format throws an exception`() {
        assertThrows<IllegalArgumentException> { ShapeFactoryImpl().createShape("Ellipse Green 23 -3 -20 -10") }
        assertThrows<IllegalArgumentException> { ShapeFactoryImpl().createShape("Rectangle Red 0 100 -153 -193") }
        assertThrows<IllegalArgumentException> { ShapeFactoryImpl().createShape("RegularPolygon Green -23 -3 -20 -10") }
        assertThrows<IllegalArgumentException> { ShapeFactoryImpl().createShape("Ellipse Green") }
        assertThrows<IllegalArgumentException> { ShapeFactoryImpl().createShape("Triangle Pink") }
        assertThrows<IllegalArgumentException> { ShapeFactoryImpl().createShape("Hello World!!!") }
    }

    @Test
    fun `Ellipse creation testing`() {
        val description = "Ellipse Green 23 -3 20 10"
        val factory = ShapeFactoryImpl()

        val expectedEllipse = Ellipse(Color.Green, Point(23, -3), 20 ,10)
        val actualEllipse = factory.createShape(description)

        Assertions.assertEquals(expectedEllipse, actualEllipse)
    }

    @Test
    fun `Rectangle creation testing`() {
        val description = "Rectangle Red 0 100 153 -1"
        val factory = ShapeFactoryImpl()

        val expectedRectangle = Rectangle(Color.Red, Point(0, 100), Point(153, -1))
        val actualRectangle: Rectangle = factory.createShape(description) as Rectangle

        Assertions.assertEquals(expectedRectangle, actualRectangle)
    }

    @Test
    fun `Regular polygon creation testing`() {
        val description = "RegularPolygon Pink 10 322 1337 20"
        val factory = ShapeFactoryImpl()

        Assertions.assertEquals(
            RegularPolygon(Color.Pink, 10, Point(322, 1337), 20),
            factory.createShape(description) as RegularPolygon
        )
    }

    @Test
    fun `Triangle polygon creation testing`() {
        val description = "Triangle Green 1 1 2 3 4 5"
        val factory = ShapeFactoryImpl()

        Assertions.assertEquals(
            Triangle(Color.Green, Point(1, 1), Point(2, 3), Point(4, 5)),
            factory.createShape(description) as Triangle
        )
    }
}
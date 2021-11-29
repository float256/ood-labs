package slides.shape.composite

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import slides.canvas.Point
import slides.canvas.RGBAColorConstants
import slides.canvas.StrokeParameters
import slides.shape.Frame
import slides.shape.Rectangle
import slides.shape.composite.dummy.DummyCanvas

class TestCompositeShapeImpl {
    @Test
    fun `Test drawing one object works correctly`() {
        val compositeShape = CompositeShapeImpl()
        val canvas = DummyCanvas()

        compositeShape.add(
            Rectangle(
                Point(0.0, 0.0), 10.0, 10.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )
        compositeShape.draw(canvas)
        Assertions.assertEquals(
            "Polygon (0.0,0.0 10.0,0.0 10.0,10.0 0.0,10.0 0.0,0.0), rgba(255, 255, 255, 0.0), StrokeParameters (rgba(255, 255, 255, 1.0), 1.0)",
            canvas.output
        )
    }

    @Test
    fun `Adding one shape twice throws exception`() {
        val compositeShape = CompositeShapeImpl()
        val shape = Rectangle(
            Point(0.0, 0.0), 10.0, 10.0,
            RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
        )

        compositeShape.add(shape)
        assertThrows<Exception> { compositeShape.add(shape) }
    }

    @Test
    fun `Adding one composite shape twice throws exception`() {
        val compositeShape = CompositeShapeImpl()
        val shape = CompositeShapeImpl()

        compositeShape.add(shape)
        assertThrows<Exception> { compositeShape.add(shape) }
    }

    @Test
    fun `Adding one composite shape if it's already added to nested composite shape throws exception`() {
        val compositeShape = CompositeShapeImpl()
        val nestedCompositeShape = CompositeShapeImpl()
        val shape = Rectangle(
            Point(0.0, 0.0), 10.0, 10.0,
            RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
        )

        compositeShape.add(nestedCompositeShape)
        nestedCompositeShape.add(shape)
        assertThrows<Exception> { compositeShape.add(shape) }
    }

    @Test
    fun `Adding composite to itself throws exception`() {
        val compositeShape = CompositeShapeImpl()
        assertThrows<Exception> { compositeShape.add(compositeShape) }
    }

    @Test
    fun `Frame computation works correctly`() {
        val compositeShape = CompositeShapeImpl()
        val nestedCompositeShape = CompositeShapeImpl()
        nestedCompositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            ),
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )

        compositeShape.add(
            Rectangle(
                Point(44.0, 0.0), 1.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            ),
            nestedCompositeShape
        )

        Assertions.assertEquals(Frame(Point(5.0, 0.0), 110.0, 112.0), compositeShape.getFrame())
    }

    @Test
    fun `Stroke parameters if all parameters are equals returns non-nullable value`() {
        val compositeShape = CompositeShapeImpl()
        compositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            ),
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )

        Assertions.assertEquals(StrokeParameters(RGBAColorConstants.WHITE, 1.0), compositeShape.getStroke())
    }

    @Test
    fun `Stroke parameters if stroke color values are different returns stroke with nullable value`() {
        val compositeShape = CompositeShapeImpl()

        val nestedCompositeShape = CompositeShapeImpl()
        nestedCompositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            ),
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )

        compositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.BLACK, 1.0)
            ),
            nestedCompositeShape,
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )

        Assertions.assertEquals(
            StrokeParameters(null, 1.0),
            compositeShape.getStroke()
        )
    }

    @Test
    fun `Stroke parameters if stroke width values are different returns stroke with nullable color`() {
        val nestedCompositeShape = CompositeShapeImpl()
        nestedCompositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            ),
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )

        val compositeShape = CompositeShapeImpl()
        compositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.1)
            ),
            nestedCompositeShape,
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.0)
            )
        )

        Assertions.assertEquals(
            StrokeParameters(RGBAColorConstants.WHITE, null),
            compositeShape.getStroke()
        )
    }

    @Test
    fun `Stroke parameters if all stroke values are different returns stroke with all null values`() {
        val compositeShape = CompositeShapeImpl()
        compositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.WHITE, 1.1)
            ),
            Rectangle(
                Point(100.0, 100.0), 15.0, 12.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.BLACK, 1.0)
            )
        )

        Assertions.assertEquals(
            StrokeParameters(null, null),
            compositeShape.getStroke()
        )
    }

    @Test
    fun `Shape removing works correctly`() {
        val compositeShape = CompositeShapeImpl()
        compositeShape.add(
            Rectangle(
                Point(5.0, 100.0), 5.0, 1.0,
                RGBAColorConstants.FULLY_TRANSPARENT, StrokeParameters(RGBAColorConstants.BLACK, 1.0)
            )
        )
        compositeShape.remove(0)

        Assertions.assertTrue(compositeShape.getAllShapes().isEmpty())
    }
}
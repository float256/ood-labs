package adapter

import modern_graphics_lib.ModernGraphicsRenderer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestModernGraphicsCanvas {
    @Test
    fun `Drawing line with specified start position`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val modernGraphicsRenderer = ModernGraphicsRenderer(printStream)
        modernGraphicsRenderer.use {
            val canvas = ModernGraphicCanvas(modernGraphicsRenderer)
            canvas.moveTo(1, 1)
            canvas.setColor(0xffffff)
            canvas.lineTo(3, 3)
            canvas.lineTo(4, 5)
        }

        val expectedResult = """
            <draw>
            <line fromX="1" fromY="1" toX="3" toY="3">
                <color r="1.0" g="1.0" b="1.0" a="1.0" />
            </line>
            <line fromX="3" fromY="3" toX="4" toY="5">
                <color r="1.0" g="1.0" b="1.0" a="1.0" />
            </line>
            </draw>
            
            """.trimIndent()
        val actualResult = outputStream.toString()
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Attempt to draw line without specifying start position throws exception`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val modernGraphicsRenderer = ModernGraphicsRenderer(printStream)
        val canvas = ModernGraphicCanvas(modernGraphicsRenderer)

        assertThrows<UnsupportedOperationException> {
            canvas.lineTo(1, 1)
        }
    }

    @Test
    fun `Canvas changes position after drawing line`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val modernGraphicsRenderer = ModernGraphicsRenderer(printStream)
        modernGraphicsRenderer.use {
            val canvas = ModernGraphicCanvas(modernGraphicsRenderer)
            canvas.moveTo(1, 2)
            canvas.setColor(0xffffff)
            canvas.lineTo(3, 4)
            canvas.lineTo(5, 6)
        }

        val expectedResult = """
            <draw>
            <line fromX="1" fromY="2" toX="3" toY="4">
                <color r="1.0" g="1.0" b="1.0" a="1.0" />
            </line>
            <line fromX="3" fromY="4" toX="5" toY="6">
                <color r="1.0" g="1.0" b="1.0" a="1.0" />
            </line>
            </draw>
            
            """.trimIndent()
        val actualResult = outputStream.toString()

        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `If color wasn't specified, #000000 is used`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val modernGraphicsRenderer = ModernGraphicsRenderer(printStream)
        modernGraphicsRenderer.use {
            val canvas = ModernGraphicCanvas(modernGraphicsRenderer)
            canvas.moveTo(1, 2)
            canvas.lineTo(5, 6)
        }

        val expectedResult = """
            <draw>
            <line fromX="1" fromY="2" toX="5" toY="6">
                <color r="0.0" g="0.0" b="0.0" a="1.0" />
            </line>
            </draw>
            
            """.trimIndent()
        val actualResult = outputStream.toString()

        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `RGBA color computed correctly`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val modernGraphicsRenderer = ModernGraphicsRenderer(printStream)
        modernGraphicsRenderer.use {
            val canvas = ModernGraphicCanvas(modernGraphicsRenderer)
            canvas.setColor(0x194726)
            canvas.moveTo(1, 2)
            canvas.lineTo(5, 6)
        }

        val expectedResult = """
            <draw>
            <line fromX="1" fromY="2" toX="5" toY="6">
                <color r="${0x19 / 255.0}" g="${0x47 / 255.0}" b="${0x26 / 255.0}" a="1.0" />
            </line>
            </draw>
            
            """.trimIndent()
        val actualResult = outputStream.toString()

        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Incorrect specified color throws exception`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val modernGraphicsRenderer = ModernGraphicsRenderer(printStream)
        val canvas = ModernGraphicCanvas(modernGraphicsRenderer)

        assertThrows<IllegalArgumentException> {
            canvas.setColor(0x1000000)
        }
    }
}

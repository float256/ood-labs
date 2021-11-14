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

        val adapter = ModernGraphicCanvasRenderer(printStream)
        adapter.use {
            adapter.moveTo(1, 1)
            adapter.lineTo(3, 3)
        }

        val expectedResult = """
            <draw>
            <line fromX="1" fromY="1" toX="3" toY="3" />
            </draw>
            
            """.trimIndent()
        val actualResult = outputStream.toString()
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Attempt to draw line without specifying start position throws exception`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val canvas = ModernGraphicCanvasRenderer(printStream)

        assertThrows<UnsupportedOperationException> {
            canvas.lineTo(1, 1)
        }
    }

    @Test
    fun `Canvas changes position after drawing line`() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        val adapter = ModernGraphicCanvasRenderer(printStream)
        adapter.use {
            adapter.moveTo(1, 2)
            adapter.lineTo(3, 4)
            adapter.lineTo(5, 6)
        }

        val expectedResult = """
            <draw>
            <line fromX="1" fromY="2" toX="3" toY="4" />
            <line fromX="3" fromY="4" toX="5" toY="6" />
            </draw>
            
            """.trimIndent()
        val actualResult = outputStream.toString()

        Assertions.assertEquals(expectedResult, actualResult)
    }
}

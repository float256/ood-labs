package modern_graphics_lib

import java.io.PrintStream

class ModernGraphicsRenderer(private val printStream: PrintStream): AutoCloseable {
    private var drawing = false

    fun beginDraw() {
        if (drawing) {
            throw UnsupportedOperationException("Drawing as already begun")
        }
        printStream.println("<draw>")
        drawing = true
    }

    fun drawLine(start: Point, end: Point) {
        if (!drawing) {
            throw UnsupportedOperationException("DrawLine is allowed between BeginDraw()/EndDraw() only")
        }
        printStream.println("""<line fromX="${start.x}" fromY="${start.y}" toX="${end.x}" toY="${end.y}" />""")
    }

    fun endDraw() {
        if (!drawing) {
            throw UnsupportedOperationException("Drawing has not been started")
        }
        printStream.println("</draw>")
        drawing = false
    }

    override fun close() {
        if (drawing) {
            endDraw()
        }
    }
}
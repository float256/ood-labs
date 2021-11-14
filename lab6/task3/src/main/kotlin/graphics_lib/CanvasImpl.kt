package graphics_lib

import java.io.PrintStream

class CanvasImpl(
    private var printStream: PrintStream
): Canvas {
    override fun moveTo(x: Int, y: Int) {
        printStream.println("MoveTo ($x, $y)")
    }

    override fun lineTo(x: Int, y: Int) {
        printStream.println("LineTo ($x, $y)")
    }

    override fun setColor(rgbColor: Int) {
        if (rgbColor in (0..0xffffff)) {
            printStream.println("Color #${Integer.toHexString(rgbColor)}")
        }
    }
}
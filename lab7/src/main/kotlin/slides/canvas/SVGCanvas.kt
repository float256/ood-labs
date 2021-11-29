package slides.canvas

import java.io.PrintStream
import java.lang.UnsupportedOperationException

class SVGCanvas(
    private val out: PrintStream
) : Canvas {
    private var isDrawEnded = false

    override var fillColor: RGBAColor? = RGBAColorConstants.FULLY_TRANSPARENT
    override var strokeParameters = StrokeParameters(RGBAColorConstants.WHITE, 1.0)

    init {
        out.println("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">")
    }

    override fun drawLine(start: Point, end: Point) {
        assertIsNotClosed()
        out.println(
            """
            <line x1="${start.x}" y1="${start.y}" 
                  x2="${start.x}"  y2="${start.y}"
                  ${getStrokeAsSVG(strokeParameters)} ${getFillColorAsSVG(fillColor)} />
            """.trimIndent()
        )
    }

    override fun drawEllipse(center: Point, width: Double, height: Double) {
        assertIsNotClosed()
        out.println(
            """
            <ellipse cx="${center.x}" cy="${center.y}" rx="${width / 2}" ry="${height / 2}"
                ${getStrokeAsSVG(strokeParameters)} ${getFillColorAsSVG(fillColor)} />
            """.trimIndent()
        )
    }

    override fun drawPolygon(points: List<Point>) {
        assertIsNotClosed()
        out.println(
            """
            <polyline points="${points.joinToString(" ")}" 
                ${getStrokeAsSVG(strokeParameters)} ${getFillColorAsSVG(fillColor)} />
            """.trimIndent()
        )
    }

    private fun assertIsNotClosed() {
        if (isDrawEnded) {
            throw UnsupportedOperationException("Drawing was finished")
        }
    }

    private fun getStrokeAsSVG(strokeParameters: StrokeParameters): String {
        if ((strokeParameters.width != null) && (strokeParameters.color != null)) {
            return """
                stroke="${strokeParameters.color}" stroke-width="${strokeParameters.width}"
                """.trimIndent()
        } else {
            return ""
        }
    }

    private fun getFillColorAsSVG(fillColor: RGBAColor?): String {
        val color = fillColor ?: RGBAColorConstants.FULLY_TRANSPARENT
        return """
                fill="$color"
                """.trimIndent()
    }

    override fun close() {
        when (isDrawEnded) {
            false -> {
                out.println("</svg>")
                isDrawEnded = false
            }
            true -> throw UnsupportedOperationException("The close method has already been called.")
        }
    }
}
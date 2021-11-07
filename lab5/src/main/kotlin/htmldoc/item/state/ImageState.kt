package htmldoc.item.state

import htmldoc.HtmlDocConstants
import java.nio.file.Path

class ImageState(
    width: Int,
    height: Int,
    val path: Path
) {
    var width = width
        set(value) {
            assertParameterInAllowedRange(value)
            field = value
        }

    var height = height
        set(value) {
            assertParameterInAllowedRange(value)
            field = value
        }

    private fun assertParameterInAllowedRange(parameter: Int) {
        if (parameter !in HtmlDocConstants.ALLOWED_RANGE_FOR_IMAGE_SIZE) {
            throw IllegalArgumentException("Incorrect image parameters")
        }
    }
}
package htmldoc.htmlgenerator

import com.google.common.escape.Escaper
import com.google.common.html.HtmlEscapers
import htmldoc.HtmlDocContent
import htmldoc.item.HtmlDocItem
import htmldoc.item.ImageItem
import htmldoc.item.ParagraphItem
import htmldoc.resource.ResourceCopyContainer

class HtmlGeneratorImpl : HtmlGenerator {
    override fun generate(docContent: HtmlDocContent, copyContainer: ResourceCopyContainer): String {
        val escaper = HtmlEscapers.htmlEscaper()
        return """
            <!DOCTYPE html>
            <html>
                <head>
                    <meta charset="UTF-8">
                    <title>${escaper.escape(docContent.title)}</title>
                </head>
                <body>
                      ${docContent.items.joinToString("\n") { generatePerOneItem(it, copyContainer, escaper) }}    
                </body>
            </html>
        """.trimIndent()
    }

    private fun generatePerOneItem(item: HtmlDocItem, copyContainer: ResourceCopyContainer, escaper: Escaper): String {
        return when (item) {
            is ParagraphItem -> {
                val text = escaper.escape(item.text)
                """<p>$text</p>"""
            }
            is ImageItem -> {
                val pathToFile = escaper.escape(copyContainer.getCopy(item.path).toAbsolutePath().toString())
                val width = item.width
                val height = item.height
                """<img src="$pathToFile" width=$width height=$height>"""
            }
            else -> throw UnsupportedOperationException("Unknown item type")
        }
    }
}
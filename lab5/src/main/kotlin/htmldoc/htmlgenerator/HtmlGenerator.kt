package htmldoc.htmlgenerator

import htmldoc.HtmlDocContent
import htmldoc.resource.ResourceCopyContainer

interface HtmlGenerator {
    fun generate(docContent: HtmlDocContent, copyContainer: ResourceCopyContainer): String
}
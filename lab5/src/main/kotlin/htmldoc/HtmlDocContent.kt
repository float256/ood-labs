package htmldoc

import htmldoc.item.HtmlDocItem

class HtmlDocContent(
    val items: MutableList<HtmlDocItem> = arrayListOf(),
    var title: String = HtmlDocConstants.DEFAULT_TITLE
)

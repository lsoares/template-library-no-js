package bydisplayvalue

import getBy
import org.jsoup.nodes.Element

fun Element.getByDisplayValue(text: String, selector: String? = null): Element =
    queryAllByDisplayValue(text = text, selector = selector).getBy()

fun Element.getByDisplayValue(text: Regex, selector: String? = null): Element =
    queryAllByDisplayValue(text = text, selector = selector).getBy()

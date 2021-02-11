package bydisplayvalue

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByDisplayValue(text: String, selector: String? = null): List<Element> =
    queryAllByDisplayValue(text = text, selector = selector).getAllBy()

fun Element.getAllByDisplayValue(text: Regex, selector: String? = null): List<Element> =
    queryAllByDisplayValue(text = text, selector = selector).getAllBy()
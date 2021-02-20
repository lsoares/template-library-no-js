package bydisplayvalue

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByDisplayValue(text: String, selector: String = "*", exact: Boolean = true): List<Element> =
    queryAllByDisplayValue(text = text, selector = selector, exact = exact).getAllBy()

fun Element.getAllByDisplayValue(text: Regex, selector: String = "*"): List<Element> =
    queryAllByDisplayValue(text = text, selector = selector).getAllBy()
package bydisplayvalue

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByDisplayValue(text: String, selector: String = "*") =
    queryAllByDisplayValue(text = text, selector = selector).queryBy()

fun Element.queryByDisplayValue(text: Regex, selector: String = "*") =
    queryAllByDisplayValue(text = text, selector = selector).queryBy()
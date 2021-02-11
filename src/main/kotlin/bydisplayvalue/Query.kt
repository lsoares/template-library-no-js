package bydisplayvalue

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByDisplayValue(text: String, selector: String? = null) =
    queryAllByDisplayValue(text = text, selector = selector).queryBy()

fun Element.queryByDisplayValue(text: Regex, selector: String? = null) =
    queryAllByDisplayValue(text = text, selector = selector).queryBy()
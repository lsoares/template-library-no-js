package bylabeltext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByLabelText(text: Regex, selector: String = "*"): Element? =
    queryAllByLabelText(text = text, selector = selector).queryBy()

fun Element.queryByLabelText(selector: String = "*", text: (Element) -> Boolean): Element? =
    queryAllByLabelText(selector = selector, text = text).queryBy()

fun Element.queryByLabelText(text: String, exact: Boolean = true, selector: String = "*"): Element? =
    queryAllByLabelText(text = text, exact = exact, selector = selector).queryBy()

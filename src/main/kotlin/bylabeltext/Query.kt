package bylabeltext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByLabelText(text: Regex): Element? =
    queryAllByLabelText(text).queryBy()

fun Element.queryByLabelText(selector: String? = null, text: (Element) -> Boolean): Element? =
    queryAllByLabelText(selector, text).queryBy()

fun Element.queryByLabelText(text: String, exact: Boolean = true, selector: String? = null): Element? =
    queryAllByLabelText(text, exact, selector).queryBy()

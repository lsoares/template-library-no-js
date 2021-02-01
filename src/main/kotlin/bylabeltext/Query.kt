package bylabeltext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByLabelText(text: Regex): Element? =
    queryAllByLabelText(text).queryBy()

fun Element.queryByLabelText(text: (Element) -> Boolean): Element? =
    queryAllByLabelText(null, text).queryBy()

fun Element.queryByLabelText(text: String, exact: Boolean = true, selector: String? = null): Element? =
    queryAllByLabelText(text, exact, selector).queryBy()

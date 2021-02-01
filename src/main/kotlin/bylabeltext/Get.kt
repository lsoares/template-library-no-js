package bylabeltext

import getBy
import org.jsoup.nodes.Element

fun Element.getByLabelText(text: String, exact: Boolean = true, selector: String? = null): Element =
    queryAllByLabelText(text, exact, selector).getBy()

fun Element.getByLabelText(text: Regex): Element =
    queryAllByLabelText(text).getBy()

fun Element.getByLabelText(selector: String? = null, text: (Element) -> Boolean): Element =
    queryAllByLabelText(selector, text).getBy()

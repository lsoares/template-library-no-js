package bylabeltext

import getBy
import org.jsoup.nodes.Element

fun Element.getByLabelText(text: String, exact: Boolean = true, selector: String? = null): Element =
    queryAllByLabelText(text = text, exact = exact, selector = selector).getBy()

fun Element.getByLabelText(text: Regex, selector: String? = null): Element =
    queryAllByLabelText(text = text, selector = selector).getBy()

fun Element.getByLabelText(selector: String? = null, text: (Element) -> Boolean): Element =
    queryAllByLabelText(selector = selector, text = text).getBy()

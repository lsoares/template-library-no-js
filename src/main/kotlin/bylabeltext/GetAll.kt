package bylabeltext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByLabelText(text: String, selector: String? = null, exact: Boolean = true): List<Element> =
    queryAllByLabelText(text = text, exact = exact, selector = selector).getAllBy()

fun Element.getAllByLabelText(text: Regex, selector: String? = null): List<Element> =
    queryAllByLabelText(text = text, selector = selector).getAllBy()

fun Element.getAllByLabelText(selector: String? = null, text: (Element) -> Boolean): List<Element> =
    queryAllByLabelText(selector = selector, text = text).getAllBy()

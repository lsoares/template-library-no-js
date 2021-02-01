package bylabeltext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByLabelText(
    text: String,
    selector: String? = null,
    exact: Boolean = true
): List<Element> =
    queryAllByLabelText(text, exact, selector).getAllBy()

fun Element.getAllByLabelText(text: Regex): List<Element> =
    queryAllByLabelText(text).getAllBy()

fun Element.getAllByLabelText(selector: String? = null, text: (Element) -> Boolean): List<Element> =
    queryAllByLabelText(selector, text).getAllBy()

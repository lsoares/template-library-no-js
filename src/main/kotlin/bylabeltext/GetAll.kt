package bylabeltext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByLabelText(text: String, exact: Boolean = true, selector: String? = null): List<Element> =
    queryAllByLabelText(text, exact, selector).getAllBy()

fun Element.getAllByLabelText(text: Regex): List<Element> =
    queryAllByLabelText(text).getAllBy()

fun Element.getAllByLabelText(text: (Element) -> Boolean): List<Element> =
    queryAllByLabelText(null, text).getAllBy()

package bylabeltext

import getBy
import org.jsoup.nodes.Element

fun Element.getByLabelText(text: String, exact: Boolean = true, selector: String = "*"): Element =
    queryAllByLabelText(text = text, exact = exact, selector = selector).getBy()

fun Element.getByLabelText(text: Regex, selector: String = "*"): Element =
    queryAllByLabelText(text = text, selector = selector).getBy()

fun Element.getByLabelText(selector: String = "*", text: (Element) -> Boolean): Element =
    queryAllByLabelText(text = text, selector = selector).getBy()

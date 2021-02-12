package byplaceholdertext

import getBy
import org.jsoup.nodes.Element

fun Element.getByPlaceholderText(text: String, selector: String = "*", exact: Boolean = true): Element =
    queryAllByPlaceholderText(text = text, selector = selector, exact = exact).getBy()

fun Element.getByPlaceholderText(text: Regex, selector: String = "*"): Element =
    queryAllByPlaceholderText(text = text, selector = selector).getBy()

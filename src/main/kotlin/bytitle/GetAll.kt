package bytitle

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByTitle(text: String, selector: String? = null, exact: Boolean = false): List<Element> =
    queryAllByTitle(text = text, selector = selector, exact = exact).getAllBy()

fun Element.getAllByTitle(text: Regex, selector: String? = null): List<Element> =
    queryAllByTitle(text = text, selector = selector).getAllBy()
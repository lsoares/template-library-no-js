package bytitle

import getBy
import org.jsoup.nodes.Element

fun Element.getByTitle(text: String, selector: String = "*", exact: Boolean = true): Element =
    queryAllByTitle(text = text, selector = selector, exact = exact).getBy()

fun Element.getByTitle(text: Regex, selector: String = "*"): Element =
    queryAllByTitle(text = text, selector = selector).getBy()

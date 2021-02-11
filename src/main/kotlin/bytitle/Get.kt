package bytitle

import getBy
import org.jsoup.nodes.Element

fun Element.getByTitle(text: String, selector: String? = null, exact: Boolean = true): Element =
    queryAllByTitle(text = text, selector = selector, exact = exact).getBy()

fun Element.getByTitle(text: Regex, selector: String? = null): Element =
    queryAllByTitle(text = text, selector = selector).getBy()

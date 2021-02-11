package bytitle

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByTitle(text: String, selector: String? = null, exact: Boolean = true) =
    queryAllByTitle(text = text, selector = selector, exact = exact).queryBy()

fun Element.queryByTitle(text: Regex, selector: String? = null) =
    queryAllByTitle(text = text, selector = selector).queryBy()
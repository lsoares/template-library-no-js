package bytitle

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByTitle(text: String, selector: String = "*", exact: Boolean = true) =
    queryAllByTitle(text = text, selector = selector, exact = exact).queryBy()

fun Element.queryByTitle(text: Regex, selector: String = "*") =
    queryAllByTitle(text = text, selector = selector).queryBy()
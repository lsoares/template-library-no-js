package byplaceholdertext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByPlaceholderText(text: String, selector: String = "*", exact: Boolean = true) =
    queryAllByPlaceholderText(text = text, selector = selector, exact = exact).queryBy()

fun Element.queryByPlaceholderText(text: Regex, selector: String = "*") =
    queryAllByPlaceholderText(text = text, selector = selector).queryBy()
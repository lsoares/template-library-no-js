package bytext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByText(text: String, selector: String = "*", exact: Boolean = true) =
    queryAllByText(text = text, selector = selector, exact = exact).queryBy()

fun Element.queryByText(text: Regex, selector: String = "*") =
    queryAllByText(text = text, selector = selector).queryBy()
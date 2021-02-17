package bytext

import getBy
import org.jsoup.nodes.Element

fun Element.getByText(text: String, selector: String = "*", exact: Boolean = true): Element =
    queryAllByText(text = text, selector = selector, exact = exact).getBy()

fun Element.getByText(text: Regex, selector: String = "*"): Element =
    queryAllByText(text = text, selector = selector).getBy()

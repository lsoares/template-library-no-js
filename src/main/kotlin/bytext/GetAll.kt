package bytext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByText(text: String, selector: String = "*", exact: Boolean = true): List<Element> =
    queryAllByText(text = text, selector = selector, exact = exact).getAllBy()

fun Element.getAllByText(text: Regex, selector: String = "*"): List<Element> =
    queryAllByText(text = text, selector = selector).getAllBy()
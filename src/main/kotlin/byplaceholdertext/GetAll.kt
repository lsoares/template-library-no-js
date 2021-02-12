package byplaceholdertext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByPlaceholderText(text: String, selector: String = "*", exact: Boolean = true): List<Element> =
    queryAllByPlaceholderText(text = text, selector = selector, exact = exact).getAllBy()

fun Element.getAllByPlaceholderText(text: Regex, selector: String = "*"): List<Element> =
    queryAllByPlaceholderText(text = text, selector = selector).getAllBy()
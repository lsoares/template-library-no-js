package byplaceholdertext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByPlaceholderText(text: String, selector: String = "*"): List<Element> =
    queryAllByPlaceholderText(text = text, selector = selector).getAllBy()

fun Element.getAllByPlaceholderText(text: Regex, selector: String = "*"): List<Element> =
    queryAllByPlaceholderText(text = text, selector = selector).getAllBy()
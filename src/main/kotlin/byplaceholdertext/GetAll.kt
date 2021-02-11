package byplaceholdertext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByPlaceholderText(text: String, selector: String? = null): List<Element> =
    queryAllByPlaceholderText(text = text, selector = selector).getAllBy()

fun Element.getAllByPlaceholderText(text: Regex, selector: String? = null): List<Element> =
    queryAllByPlaceholderText(text = text, selector = selector).getAllBy()
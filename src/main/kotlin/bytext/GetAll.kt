package bytext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByText(text: String, selector: String? = null): List<Element> =
    queryAllByText(text = text, selector = selector).getAllBy()

fun Element.getAllByText(text: Regex, selector: String? = null): List<Element> =
    queryAllByText(text = text, selector = selector).getAllBy()
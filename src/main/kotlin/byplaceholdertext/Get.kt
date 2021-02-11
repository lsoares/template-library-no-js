package byplaceholdertext

import getBy
import org.jsoup.nodes.Element

fun Element.getByPlaceholderText(text: String, selector: String? = null): Element =
    queryAllByPlaceholderText(text = text, selector = selector).getBy()

fun Element.getByPlaceholderText(text: Regex, selector: String? = null): Element =
    queryAllByPlaceholderText(text = text, selector = selector).getBy()

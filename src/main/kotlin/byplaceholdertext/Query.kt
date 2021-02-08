package byplaceholdertext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByPlaceholderText(text: String, selector: String = "*") =
    queryAllByPlaceholderText(text = text, selector = selector).queryBy()

fun Element.queryByPlaceholderText(text: Regex, selector: String = "*") =
    queryAllByPlaceholderText(text = text, selector = selector).queryBy()
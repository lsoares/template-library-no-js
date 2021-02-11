package byplaceholdertext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByPlaceholderText(text: String, selector: String? = null) =
    queryAllByPlaceholderText(text = text, selector = selector).queryBy()

fun Element.queryByPlaceholderText(text: Regex, selector: String? = null) =
    queryAllByPlaceholderText(text = text, selector = selector).queryBy()
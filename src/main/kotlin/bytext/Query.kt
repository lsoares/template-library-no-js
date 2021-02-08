package bytext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByText(text: String, selector: String = "*") =
    queryAllByText(text = text, selector = selector).queryBy()

fun Element.queryByText(text: Regex, selector: String = "*") =
    queryAllByText(text = text, selector = selector).queryBy()
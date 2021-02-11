package bytext

import getBy
import org.jsoup.nodes.Element

fun Element.getByText(text: String, selector: String? = null): Element =
    queryAllByText(text = text, selector = selector).getBy()

fun Element.getByText(text: Regex, selector: String? = null): Element =
    queryAllByText(text = text, selector = selector).getBy()

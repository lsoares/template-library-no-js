package bytitle

import getBy
import org.jsoup.nodes.Element

fun Element.getByTitle(text: String, selector: String = "*"): Element =
    queryAllByTitle(text = text, selector = selector).getBy()

fun Element.getByTitle(text: Regex, selector: String = "*"): Element =
    queryAllByTitle(text = text, selector = selector).getBy()

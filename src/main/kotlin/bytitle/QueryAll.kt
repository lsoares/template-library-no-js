package bytitle

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByTitle(
    text: String,
    selector: String = "*",
    exact: Boolean = false,
): List<Element> =
    when (exact) {
        true -> getElementsByAttributeValue("title", text)
        false -> getElementsByAttributeValueContaining("title", text)
    }.filterBySelector(selector)

fun Element.queryAllByTitle(
    text: Regex,
    selector: String = "*",
): List<Element> =
    getElementsByAttributeValueMatching("title", text.toPattern())
        .filterBySelector(selector)
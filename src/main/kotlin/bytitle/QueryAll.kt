package bytitle

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByTitle(
    text: String,
    selector: String? = null,
    exact: Boolean = false,
): List<Element> =
    when (exact) {
        true -> getElementsByAttributeValue("title", text)
        false -> getElementsByAttributeValueContaining("title", text)
    }.filterBySelector(selector)

fun Element.queryAllByTitle(
    text: Regex,
    selector: String? = null,
): List<Element> =
    getElementsByAttributeValueMatching("title", text.toPattern())
        .filterBySelector(selector)
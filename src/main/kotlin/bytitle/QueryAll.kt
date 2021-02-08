package bytitle

import org.jsoup.nodes.Element

fun Element.queryAllByTitle(
    text: String,
    selector: String = "*",
): List<Element> =
    getElementsByAttributeValue("title", text)
        .select(selector)

fun Element.queryAllByTitle(
    text: Regex,
    selector: String = "*",
): List<Element> =
    getElementsByAttributeValueMatching("title", text.toPattern())
        .select(selector)
package byplaceholdertext

import org.jsoup.nodes.Element

fun Element.queryAllByPlaceholderText(
    text: String,
    selector: String = "*",
): List<Element> =
    getElementsByAttributeValue("placeholder", text)
        .select(selector)


fun Element.queryAllByPlaceholderText(
    text: Regex,
    selector: String = "*",
): List<Element> =
    getElementsByAttributeValueMatching("placeholder", text.toPattern())
        .select(selector)
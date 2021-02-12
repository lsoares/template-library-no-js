package byplaceholdertext

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByPlaceholderText(text: String, selector: String = "*", exact: Boolean = true): List<Element> =
    (when (exact) {
        true -> getElementsByAttributeValue("placeholder", text)
        false -> getElementsByAttributeValueContaining("placeholder", text)
    }).filterBySelector(selector)

fun Element.queryAllByPlaceholderText(text: Regex, selector: String = "*"): List<Element> =
    getElementsByAttributeValueMatching("placeholder", text.toPattern())
        .filterBySelector(selector)

package byplaceholdertext

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByPlaceholderText(text: String, selector: String? = null): List<Element> =
    getElementsByAttributeValue("placeholder", text)
        .filterBySelector(selector)

fun Element.queryAllByPlaceholderText(text: Regex, selector: String? = null): List<Element> =
    getElementsByAttributeValueMatching("placeholder", text.toPattern())
        .filterBySelector(selector)

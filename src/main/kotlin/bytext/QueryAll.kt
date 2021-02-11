package bytext

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByText(
    text: String,
    selector: String? = null,
): List<Element> =
    getElementsContainingOwnText(text)
        .filterBySelector(selector)
        .filter { it.ownText() == text }

fun Element.queryAllByText(
    text: Regex,
    selector: String? = null,
): List<Element> =
    getElementsMatchingOwnText(text.toPattern())
        .filterBySelector(selector)
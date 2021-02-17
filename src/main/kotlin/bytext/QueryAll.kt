package bytext

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByText(
    text: String,
    selector: String = "*",
    exact: Boolean = true,
): List<Element> =
    getElementsContainingOwnText(text)
        .filterBySelector(selector)
        .filter { !exact || it.ownText() == text }

fun Element.queryAllByText(
    text: Regex,
    selector: String = "*",
): List<Element> =
    getElementsMatchingOwnText(text.toPattern())
        .filterBySelector(selector)
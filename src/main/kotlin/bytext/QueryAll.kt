package bytext

import org.jsoup.nodes.Element

fun Element.queryAllByText(
    text: String,
    selector: String = "*",
): List<Element> =
    getElementsContainingOwnText(text)
        .select(selector)
        .filter { it.ownText() == text }

fun Element.queryAllByText(
    text: Regex,
    selector: String = "*",
): List<Element> =
    getElementsMatchingOwnText(text.toPattern())
        .select(selector)
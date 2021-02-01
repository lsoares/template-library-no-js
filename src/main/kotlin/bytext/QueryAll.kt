package bytext

import org.jsoup.nodes.Element

fun Element.queryAllByText(
    text: String,
): List<Element> =
    getElementsContainingOwnText(text)
        .filter { it.ownText() == text }

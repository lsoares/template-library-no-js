package bytext

import getAllBy
import org.jsoup.nodes.Element

fun Element.getAllByText(text: String): List<Element> =
    queryAllByText(text = text).getAllBy()
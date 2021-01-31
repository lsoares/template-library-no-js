package bylabeltext

import UndefinedResult
import org.jsoup.nodes.Element

fun Element.getByLabelText(text: String): Element =
    queryAllByLabelText(text)
        .singleOrNull()
        ?: throw UndefinedResult()

fun Element.getAllByLabelText(text: String): List<Element> =
    queryAllByLabelText(text)
        .takeIf { it.isNotEmpty() }
        ?: throw UndefinedResult()

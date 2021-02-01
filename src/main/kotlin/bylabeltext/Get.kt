package bylabeltext

import UndefinedResult
import org.jsoup.nodes.Element

fun Element.getByLabelText(text: String, exact: Boolean = true, selector: String? = null): Element =
    queryAllByLabelText(text, exact, selector).getBy()

fun Element.getByLabelText(text: Regex): Element =
    queryAllByLabelText(text).getBy()

fun Element.getByLabelText(text: (Element) -> Boolean): Element =
    queryAllByLabelText(null, text).getBy()

fun Element.getAllByLabelText(text: String, exact: Boolean = true, selector: String? = null): List<Element> =
    queryAllByLabelText(text, exact, selector).getAllBy()

fun Element.getAllByLabelText(text: Regex): List<Element> =
    queryAllByLabelText(text).getAllBy()

fun Element.getAllByLabelText(text: (Element) -> Boolean): List<Element> =
    queryAllByLabelText(null, text).getAllBy()

internal fun List<Element>.getBy() =
    singleOrNull() ?: throw UndefinedResult()

internal fun List<Element>.getAllBy() =
    takeIf { it.isNotEmpty() } ?: throw UndefinedResult()
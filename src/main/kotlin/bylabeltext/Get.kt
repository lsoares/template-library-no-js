package bylabeltext

import UndefinedResult
import org.jsoup.nodes.Element

fun Element.getByLabelText(text: String, exact: Boolean = true): Element =
    queryAllByLabelText(text, exact).getBy()

fun Element.getByLabelText(text: Regex): Element =
    queryAllByLabelText(text).getBy()

fun Element.getAllByLabelText(text: String, exact: Boolean = true): List<Element> =
    queryAllByLabelText(text, exact).getAllBy()

fun Element.getAllByLabelText(text: Regex): List<Element> =
    queryAllByLabelText(text).getAllBy()

internal fun List<Element>.getBy() =
    singleOrNull() ?: throw UndefinedResult()

internal fun List<Element>.getAllBy() =
    takeIf { it.isNotEmpty() } ?: throw UndefinedResult()
package bylabeltext

import ByFunction
import ByRegex
import ByString
import TextMatch
import UndefinedResult
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun Element.queryByLabelText(text: String): List<Element>? =
    queryAllByLabelText(text)
        .takeIf { it.isNotEmpty() }
        ?.also { check(it.size == 1) { throw UndefinedResult() } }

fun Element.queryAllByLabelText(
    text: Regex,
    exact: Boolean = true,
    selector: String? = null,
) = queryAllByLabelText(
    text = ByRegex(text),
    exact = exact,
    selector = selector,
)

fun Element.queryAllByLabelText(
    text: String,
    exact: Boolean = true,
    selector: String? = null,
) = queryAllByLabelText(
    text = ByString(text),
    exact = exact,
    selector = selector,
)

fun Element.queryAllByLabelText(
    exact: Boolean = true,
    selector: String? = null,
    text: (String) -> Boolean,
) = queryAllByLabelText(
    text = ByFunction(text),
    exact = exact,
    selector = selector,
)

internal fun Element.queryAllByLabelText(
    text: TextMatch,
    exact: Boolean = true,
    selector: String? = null,
): List<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter { it.matcher(text, exact) }
        .map {
            it.children() + getByAriaLabelledBy(it) + listOfNotNull(getByFor(it))
        }
        .flatten()
        .filter {
            it.tagName() in selector ?: "input, select, textarea, button, output"
        }
        .toList()

private fun Element.matcher(text: TextMatch, exact: Boolean) =
    when (text) {
        is ByString -> when (exact) {
            true -> text() == text.value
            false -> text().toLowerCase().contains(text.value.toLowerCase())
        }
        is ByRegex -> text().matches(text.value)
        is ByFunction -> text.matcher.invoke(text())
    }

private fun Element.getByAriaLabelledBy(label: Element) =
    label.attr("id")
        .takeIf(String::isNotBlank)
        ?.let { getElementsByAttributeValue("aria-labelledby", it) }
        ?: Elements()

private fun Element.getByFor(label: Element) =
    label.attr("for")
        .takeIf(String::isNotBlank)
        ?.let(::getElementById)

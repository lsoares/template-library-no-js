package bylabeltext

import UndefinedResult
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun Element.queryByLabelText(text: String): List<Element>? =
    queryAllByLabelText { it.text() == text }
        .takeIf { it.isNotEmpty() }
        ?.also { check(it.size == 1) { throw UndefinedResult() } }

fun Element.queryAllByLabelText(
    text: String,
    exact: Boolean = true,
    selector: String? = null,
) = queryAllByLabelText(selector) {
    when (exact) {
        true -> it.text() == text
        false -> it.text().toLowerCase().contains(text.toLowerCase())
    }
}

fun Element.queryAllByLabelText(
    text: Regex,
    selector: String? = null,
) = queryAllByLabelText(selector) {
    it.text().matches(text)
}

fun Element.queryAllByLabelText(
    selector: String? = null,
    filter: (Element) -> Boolean,
): List<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter(filter)
        .map {
            it.children() + getByAriaLabelledBy(it) + listOfNotNull(getByFor(it))
        }
        .flatten()
        .filter { el ->
            when (selector) {
                null -> el.tagName() in setOf("input", "select", "textarea", "button", "output")
                else -> el.tagName() == selector
            }
        }
        .toList()

private fun Element.getByAriaLabelledBy(label: Element) =
    label.attr("id")
        .takeIf(String::isNotBlank)
        ?.let { getElementsByAttributeValue("aria-labelledby", it) }
        ?: Elements()

private fun Element.getByFor(label: Element) =
    label.attr("for")
        .takeIf(String::isNotBlank)
        ?.let(::getElementById)

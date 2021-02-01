package bylabeltext

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

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

fun Element.queryAllByLabelText(text: Regex, selector: String? = null) =
    queryAllByLabelText(selector) {
        it.text().matches(text)
    }

fun Element.queryAllByLabelText(
    selector: String? = null,
    text: (Element) -> Boolean,
): List<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter(text)
        .map {
            it.children() + getByAriaLabelledBy(it) + listOfNotNull(getByFor(it))
        }
        .flatten()
        .filter {
            selector == null || it.tagName() == selector
        }
        .filter {
            it.tagName() in setOf("input", "select", "textarea", "button", "output")
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
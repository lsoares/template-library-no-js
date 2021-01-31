import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.Exception

fun Element.queryByLabelText(text: String): List<Element>? =
    queryAllByLabelText(text)
        .takeIf { it.isNotEmpty() }
        ?.also { check(it.size == 1) { throw UndefinedResult() } }

fun Element.getAllByLabelText(text: String): List<Element> =
    queryAllByLabelText(text)
        .takeIf { it.isNotEmpty() }
        ?: throw UndefinedResult()

fun Element.getByLabelText(text: String): Element =
    queryAllByLabelText(text)
        .singleOrNull()
        ?: throw UndefinedResult()

fun Element.queryAllByLabelText(
    text: String? = null,
    exact: Boolean = true,
    selector: String? = null,
    textRegex: Regex? = null,
): List<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter { it.textMatcher(text, exact) }
        .filter { it.textRegexMatcher(textRegex) }
        .map {
            it.children() + getByAriaLabelledBy(it) + listOfNotNull(getByFor(it))
        }
        .flatten()
        .filter {
            it.tagName() in selector ?: "input, select, textarea, button, output"
        }
        .toList()

private fun Element.textRegexMatcher(textRegex: Regex?) =
    textRegex?.let {
        text().matches(it)
    } ?: true

private fun Element.textMatcher(text: String?, exact: Boolean) =
    text?.let {
        when (exact) {
            true -> text() == it
            false -> text().toLowerCase().contains(it.toLowerCase())
        }
    } ?: true

private fun Element.getByAriaLabelledBy(label: Element) =
    label.attr("id")
        .takeIf(String::isNotBlank)
        ?.let { getElementsByAttributeValue("aria-labelledby", it) }
        ?: Elements()

private fun Element.getByFor(label: Element) =
    label.attr("for")
        .takeIf(String::isNotBlank)
        ?.let(::getElementById)

class UndefinedResult : Exception()
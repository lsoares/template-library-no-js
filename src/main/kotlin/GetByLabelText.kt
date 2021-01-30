import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.Exception


fun Element.getByLabelText(label: String, exact: Boolean = true): Element =
    getElementsByTag("label")
        .singleOrNull { it.initialMatcher(label, exact) }
        ?.let {
            it.children() + getByAriaLabelledBy(it) + listOfNotNull(getByFor(it))
        }
        ?.singleOrNull { it.tagName() in "input, select, textarea, button, output" }
        ?: throw UndefinedResult()

private fun Element.initialMatcher(label: String, exact: Boolean) =
    when (exact) {
        true -> text() == label
        false -> text().toLowerCase().contains(label.toLowerCase())
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

class UndefinedResult : Exception()
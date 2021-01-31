import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.lang.Exception

fun Element.queryByLabelText(
    label: String,
    exact: Boolean = true,
    selector: String? = null
): List<Element>? =
    queryAllByLabelText(label, exact, selector)
        .takeIf { it.isNotEmpty() }
        ?.also { check(it.size == 1) { throw UndefinedResult() } }

fun Element.getAllByLabelText(
    label: String,
    exact: Boolean = true,
    selector: String? = null
): List<Element> =
    queryAllByLabelText(label, exact, selector)
        .takeIf { it.isNotEmpty() }
        ?: throw UndefinedResult()

fun Element.getByLabelText(
    label: String,
    exact: Boolean = true,
    selector: String? = null
): Element =
    queryAllByLabelText(label, exact, selector)
        .singleOrNull()
        ?: throw UndefinedResult()

fun Element.queryAllByLabelText(
    label: String,
    exact: Boolean = true,
    selector: String? = null
): List<Element> =
    getElementsByTag("label")
        .filter { it.initialMatcher(label, exact) }
        .map {
            it.children() + getByAriaLabelledBy(it) + listOfNotNull(getByFor(it))
        }
        .flatten()
        .filter {
            it.tagName() in selector ?: "input, select, textarea, button, output"
        }

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
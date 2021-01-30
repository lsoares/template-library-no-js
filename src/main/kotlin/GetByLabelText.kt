import org.jsoup.nodes.Element
import java.lang.Exception

fun Element.getByLabelText(label: String): Element =
    (getByFor(label) + getWrapped(label) + getByAriaLabelledBy(label))
        .singleOrNull() ?: throw UndefinedResult()

private fun Element.getByAriaLabelledBy(label: String): Sequence<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter { it.text() == label }
        .map { it.attr("id") }
        .filter(String::isNotBlank)
        .map { getElementsByAttributeValue("aria-labelledby", it) }
        .flatten()

private fun Element.getByFor(label: String): Sequence<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter { it.text() == label }
        .map { it.attr("for") }
        .filter(String::isNotBlank)
        .map(::getElementById)

private fun Element.getWrapped(label: String): Sequence<Element> =
    getElementsByTag("label")
        .asSequence()
        .filter { it.text() == label }
        .map { it.select("input, select, textarea, button, output") }
        .flatten()

class UndefinedResult : Exception()
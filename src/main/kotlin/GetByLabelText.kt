import org.jsoup.nodes.Element
import java.lang.Exception

fun Element.getByLabelText(label: String): Element =
    (getByFor(label) + getWrapped(label)).singleOrNull() ?: throw UndefinedResult()

private fun Element.getByFor(label: String) =
    getElementsByTag("label")
        .asSequence()
        .filter { it.text() == label }
        .map { it.attr("for") }
        .filter(String::isNotBlank)
        .map(::getElementById)

private fun Element.getWrapped(label: String) =
    getElementsByTag("label")
        .asSequence()
        .filter { it.text() == label }
        .map { it.select("input, select, textarea, button, output") }
        .flatten()

class UndefinedResult : Exception()
import org.jsoup.nodes.Element

fun Element.getByLabelText(label: String): Element =
    (getByFor(label) + getWrapped(label)).single() // TODO: don't filter by label twice

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
        .map { it.select("input") }
        .flatten()

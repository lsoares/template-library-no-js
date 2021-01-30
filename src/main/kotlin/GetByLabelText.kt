import org.jsoup.nodes.Element

fun Element.getByLabelText(label: String): Element =
    getElementsByTag("label")
        .single { it.text() == label }
        .attr("for")
        .let(::getElementById)

package bydisplayvalue

import org.jsoup.nodes.Element

fun Element.queryAllByDisplayValue(text: String, selector: String = "*"): List<Element> =
    getInputs(text) + getTextareas(text) + getSelects(text)

private fun Element.getInputs(text: String): List<Element> =
    getElementsByTag("input").map { it.getElementsByAttributeValue("value", text) }.flatten()

private fun Element.getTextareas(text: String): List<Element> =
    getElementsByTag("textarea").map { it.getElementsContainingOwnText(text) }.flatten()
        .filter { it.text() == text }

private fun Element.getSelects(text: String): List<Element> =
    getElementsByTag("select").filter { it.select("option[selected]").firstOrNull()?.ownText() == text }


fun Element.queryAllByDisplayValue(text: Regex, selector: String = "*"): List<Element> =
    (getInputs(text) + getTextareas(text) + getSelects(text))
        .filter { selector == "*" || it.`is`(selector) }

private fun Element.getInputs(text: Regex): List<Element> =
    getElementsByTag("input").map { it.getElementsByAttributeValueMatching("value", text.toPattern()) }.flatten()

private fun Element.getTextareas(text: Regex): List<Element> =
    getElementsByTag("textarea").map { it.getElementsMatchingOwnText(text.toPattern()) }.flatten()

private fun Element.getSelects(text: Regex): List<Element> =
    getElementsByTag("select").filter {
        it.select("option[selected]").map { it.getElementsMatchingOwnText(text.toPattern()) }.flatten().isNotEmpty()
    }



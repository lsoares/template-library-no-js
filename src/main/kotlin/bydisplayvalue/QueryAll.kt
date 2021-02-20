package bydisplayvalue

import filterBySelector
import org.jsoup.nodes.Element

fun Element.queryAllByDisplayValue(text: String, selector: String = "*", exact: Boolean = true): List<Element> =
    (getInputs(text, exact) + getTextareas(text, exact) + getSelects(text, exact))
        .filterBySelector(selector)

private fun Element.getInputs(text: String, exact: Boolean): List<Element> =
    getElementsByTag("input")
        .map {
            when (exact) {
                true -> it.getElementsByAttributeValue("value", text)
                false -> it.getElementsByAttributeValueContaining("value", text)
            }
        }
        .flatten()
        .filter { !exact || it.`val`() == text }

private fun Element.getTextareas(text: String, exact: Boolean): List<Element> =
    getElementsByTag("textarea").map { it.getElementsContainingOwnText(text) }.flatten()
        .filter {
            when (exact) {
                true -> it.text() == text
                false -> it.text().contains(text, ignoreCase = true)
            }
        }

private fun Element.getSelects(text: String, exact: Boolean): List<Element> =
    getElementsByTag("select")
        .filter {
            val selectedOption = it.select("option[selected]").firstOrNull() ?: return@filter false
            when (exact) {
                true -> selectedOption.ownText() == text
                false -> selectedOption.ownText()?.contains(text, ignoreCase = true) == true
            }
        }

fun Element.queryAllByDisplayValue(text: Regex, selector: String = "*"): List<Element> =
    (getInputs(text) + getTextareas(text) + getSelects(text))
        .filterBySelector(selector)

private fun Element.getInputs(text: Regex) =
    getElementsByTag("input")
        .map { it.getElementsByAttributeValueMatching("value", text.toPattern()) }.flatten()

private fun Element.getTextareas(text: Regex) =
    getElementsByTag("textarea")
        .map { it.getElementsMatchingOwnText(text.toPattern()) }.flatten()

private fun Element.getSelects(text: Regex) =
    getElementsByTag("select").filter {
        it.select("option[selected]").map { it.getElementsMatchingOwnText(text.toPattern()) }.flatten().isNotEmpty()
    }



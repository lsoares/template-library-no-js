package bydisplayvalue

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnlyCorrectTagsTest {

    @ParameterizedTest
    @ValueSource(strings = ["div", "section", "main"])
    fun `by text`(tag: String) {
        val doc = Jsoup.parse("<$tag value='a b c' />")

        val getByDisplayValue = { doc.getByDisplayValue("a b c") }
        val queryByDisplayValue = doc.queryByDisplayValue("a b c")
        val getAllByDisplayValue = { doc.getAllByDisplayValue("a b c") }
        val queryAllByDisplayValue = doc.queryAllByDisplayValue("a b c")

        assertThrows<UndefinedResult> { getByDisplayValue() }
        assertNull(queryByDisplayValue)
        assertThrows<UndefinedResult> { getAllByDisplayValue() }
        assertTrue(queryAllByDisplayValue.isEmpty())
    }
}
package bydisplayvalue

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoResultsTest {

    @Test
    fun `returns null when no results match`() {
        val doc = Jsoup.parse("<span />")

        val queryByDisplayValue = doc.queryByDisplayValue("Email")

        assertNull(queryByDisplayValue)
    }

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val queryAllByDisplayValue = doc.queryAllByDisplayValue("Email")

        assertTrue(queryAllByDisplayValue.isEmpty())
    }

    @Test
    fun `fails when no results match`() {
        val doc = Jsoup.parse("<span />")

        val getByDisplayValue = { doc.getByDisplayValue("Email") }
        val getAllByDisplayValue = { doc.getAllByDisplayValue("Email") }

        assertThrows(UndefinedResult::class.java) { getByDisplayValue() }
        assertThrows(UndefinedResult::class.java) { getAllByDisplayValue() }
    }
}
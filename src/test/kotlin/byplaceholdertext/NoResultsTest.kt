package byplaceholdertext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoResultsTest {

    @Test
    fun `returns null when no results match`() {
        val doc = Jsoup.parse("<span />")

        val queryByPlaceholderText = doc.queryByPlaceholderText("Email")

        assertNull(queryByPlaceholderText)
    }

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByPlaceholderText("Email")

        assertTrue(byLabelText.isEmpty())
    }

    @Test
    fun `fails when no results match`() {
        val doc = Jsoup.parse("<span />")

        val getByPlaceholderText = { doc.getByPlaceholderText("Email") }
        val getAllByPlaceholderText = { doc.getAllByPlaceholderText("Email") }

        assertThrows(UndefinedResult::class.java) { getByPlaceholderText() }
        assertThrows(UndefinedResult::class.java) { getAllByPlaceholderText() }
    }
}
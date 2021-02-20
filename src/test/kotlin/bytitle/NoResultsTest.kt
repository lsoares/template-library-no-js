package bytitle

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoResultsTest {

    @Test
    fun `returns null when no results match`() {
        val doc = Jsoup.parse("<span />")

        val queryByTitle = doc.queryByTitle("Email")

        assertNull(queryByTitle)
    }

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByTitle("Email")

        assertTrue(byLabelText.isEmpty())
    }

    @Test
    fun `fails when no results match`() {
        val doc = Jsoup.parse("<span />")

        val getByTitle = { doc.getByTitle("Email") }
        val getAllByTitle = { doc.getAllByTitle("Email") }

        assertThrows(UndefinedResult::class.java) { getByTitle() }
        assertThrows(UndefinedResult::class.java) { getAllByTitle() }
    }
}
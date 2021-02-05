package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoResultsTest {

    @Test
    fun `returns null when no results match`() {
        val doc = Jsoup.parse("<span />")

        val queryByLabelText = doc.queryByLabelText("Email")

        assertNull(queryByLabelText)
    }

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByLabelText("Email")

        assertTrue(byLabelText.isEmpty())
    }

    @Test
    fun `fails when no results match`() {
        val doc = Jsoup.parse("<span />")

        val getByLabelText = { doc.getByLabelText("Email") }
        val getAllByLabelText = { doc.getAllByLabelText("Email") }

        assertThrows(UndefinedResult::class.java) { getByLabelText() }
        assertThrows(UndefinedResult::class.java) { getAllByLabelText() }
    }
}
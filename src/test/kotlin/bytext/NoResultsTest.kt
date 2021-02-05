package bytext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoResultsTest {

    @Test
    fun `returns null when no results match`() {
        val doc = Jsoup.parse("<span />")

        val queryByText = doc.queryByText("Email")

        assertNull(queryByText)
    }

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByText("Email")

        assertTrue(byLabelText.isEmpty())
    }

    @Test
    fun `fails when no results match`() {
        val doc = Jsoup.parse("<span />")

        val getByText = { doc.getByText("Email") }
        val getAllByText = { doc.getAllByText("Email") }

        assertThrows(UndefinedResult::class.java) { getByText() }
        assertThrows(UndefinedResult::class.java) { getAllByText() }
    }
}
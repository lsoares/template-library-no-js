package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class QueryByLabelTextTest {

    @Test
    fun `returns null when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryByLabelText("Email")

        assertNull(byLabelText)
    }

    @Test
    fun `throws error when multiple results match`() {
        val doc = Jsoup.parse(
            """
            <label for="email1">Email</label>
            <input id="email1" />
            <label>Email<input id="email2" /></label>
        """
        )

        val byLabelText = { doc.queryByLabelText("Email") }

        assertThrows<UndefinedResult> { byLabelText() }
    }

    @Test
    fun `query by label - for - NOT exact`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
        """
        )

        val byLabelText = doc.queryByLabelText("email", exact = false)

        assertEquals("email", byLabelText?.single()?.id())
    }
}
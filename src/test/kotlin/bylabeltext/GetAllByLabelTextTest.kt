package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class GetAllByLabelTextTest {

    @Test
    fun `gets all inputs by label text`() {
        val doc = Jsoup.parse(
            """
            <label for="email1">Email address</label>
            <input id="email1" />
            <label>Email address<input id="email2" /></label>
        """
        )

        val byLabelText = doc.getAllByLabelText("Email address")

        assertEquals(
            """[<input id="email1">, <input id="email2">]""",
            byLabelText.toString()
        )
    }

    @Test
    fun `only form elements are returned`() {
        val doc = Jsoup.parse(
            """
            <label for="email1">Email address</label>
            <input id="email1" />
            <label>Email address<section /></label>
        """
        )

        val byLabelText = doc.getAllByLabelText("Email address")

        assertEquals("input", byLabelText.single().tagName())
    }

    @Test
    fun `fails when no results are found`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = { doc.getAllByLabelText("Email") }

        assertThrows(UndefinedResult::class.java) { byLabelText() }
    }
}
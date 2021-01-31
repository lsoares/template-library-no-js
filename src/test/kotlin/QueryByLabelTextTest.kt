import org.jsoup.Jsoup
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
}
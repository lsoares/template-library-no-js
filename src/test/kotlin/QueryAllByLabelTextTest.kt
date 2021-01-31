import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class QueryAllByLabelTextTest {

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByLabelText("Email")

        assertTrue(byLabelText.isEmpty())
    }
}
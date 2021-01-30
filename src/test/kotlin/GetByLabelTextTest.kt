import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetByLabelTextTest {

    @Test
    fun `get by label basic`() {
        val doc = Jsoup.parse("""
            <label for="email">Email address</label>
            <input id="email" />
            <label for="password">Password</label>
            <input id="password" />
        """)

        val byLabelText = doc.getByLabelText("Email address")

        assertEquals("""<input id="email">""", byLabelText.toString())
    }
}


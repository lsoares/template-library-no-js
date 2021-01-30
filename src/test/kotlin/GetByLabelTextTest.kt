import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GetByLabelTextTest {

    @Test
    fun `get by label - for`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
            <label for="password">Password</label>
            <input id="password" />
        """
        )

        val byLabelText = doc.getByLabelText("Email address")

        assertEquals("""<input id="email">""", byLabelText.toString())
    }

    @ParameterizedTest
    @ValueSource(strings = ["input", "select", "textarea", "button", "output"])
    fun `get any form element by label - for`(tag: String) {
        val doc = Jsoup.parse(
            """
                <label for='x'>by for</label>
                <$tag id='x' />
            """
        )

        val byLabelText = doc.getByLabelText("by for")

        assertEquals(tag, byLabelText.tagName())
    }

    @Test
    fun `get by label - wrapper`() {
        val doc = Jsoup.parse(
            """
            <label>Username <input id="username" /></label>
            <label>Password <input /></label>
        """
        )

        val byLabelText = doc.getByLabelText("Username")

        assertEquals("""<input id="username">""", byLabelText.toString())
    }

    @ParameterizedTest
    @ValueSource(strings = ["input", "select", "textarea", "button", "output"])
    fun `get any form element by label - wrapper`(tag: String) {
        val doc = Jsoup.parse("<label>wrapped <$tag /></label>")

        val byLabelText = doc.getByLabelText("wrapped")

        assertEquals(tag, byLabelText.tagName())
    }
}


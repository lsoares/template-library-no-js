import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
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

    @Test
    fun `fails when more than one are available`() {
        val doc = Jsoup.parse(
            """
            <label for="x">Username <input id="username" /></label>
            <label id="x"><input /></label>
        """
        )

        val byLabelText = { doc.getByLabelText("Username") }

        assertThrows(UndefinedResult::class.java) { byLabelText() }
    }

    @Test
    fun `fails when none is available`() {
        val doc = Jsoup.parse("<span></span>")

        val byLabelText = { doc.getByLabelText("Username") }

        assertThrows(UndefinedResult::class.java) { byLabelText() }
    }

    @Test
    fun `by aria labelled by`() {
        val doc = Jsoup.parse(
            """
            <label id="username-label">Username</label>
            <input aria-labelledby="username-label" />
        """
        )

        val byLabelText = doc.getByLabelText("Username")

        assertEquals("""<input aria-labelledby="username-label">""", byLabelText.toString())
    }

    // TODO Wrapper labels where the label text is in another child element
    // TODO selector
}


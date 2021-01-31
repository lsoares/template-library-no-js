package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class QueryAllByLabelTextTest {

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

        val byLabelText = doc.queryAllByLabelText("Email address")

        assertEquals("""[<input id="email">]""", byLabelText.toString())
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

        val byLabelText = doc.queryAllByLabelText("by for")

        assertEquals(tag, byLabelText.single().tagName())
    }

    @Test
    fun `get by label - wrapper`() {
        val doc = Jsoup.parse(
            """
             <label>Username <input arg="username" /></label>
             <label>Password <input /></label>
           """
        )

        val byLabelText = doc.queryAllByLabelText("Username")

        assertEquals("""[<input arg="username">]""", byLabelText.toString())
    }

    @ParameterizedTest
    @ValueSource(strings = ["input", "select", "textarea", "button", "output"])
    fun `get any form element by label - wrapper`(tag: String) {
        val doc = Jsoup.parse("<label>wrapped <$tag /></label>")

        val byLabelText = doc.queryAllByLabelText("wrapped")

        assertEquals(tag, byLabelText.single().tagName())
    }

    @Test
    fun `by aria labelled by`() {
        val doc = Jsoup.parse(
            """
            <label id="username-label">Username</label>
            <input aria-labelledby="username-label" />
        """
        )

        val byLabelText = doc.queryAllByLabelText("Username")

        assertEquals(
            """[<input aria-labelledby="username-label">]""",
            byLabelText.toString()
        )
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "<label for='x'>nope</label> <div id='x'></div>",
            "<label>nope <span /></label>",
            "<label id='x'>nope</label> <section aria-labelledby='x' />"
        ]
    )
    fun `no results with the wrong type`(html: String) {
        val doc = Jsoup.parse(html)

        val byLabelText = doc.queryAllByLabelText("nope")

        assertTrue(byLabelText.isEmpty())
    }

    @Test
    fun `get by label - for - NOT exact`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
        """
        )

        val byLabelText = doc.queryAllByLabelText("email", exact = false)

        assertEquals("""[<input id="email">]""", byLabelText.toString())
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """
            <label>
              Username
              <input />
            </label>
            <label>
              Username
              <textarea></textarea>
            </label> 
        """,
            """
            <label id="username">Username</label>
            <input aria-labelledby="username" />
            <span aria-labelledby="username">Please enter your username</span>
        """
        ]
    )
    fun `specifying a selector`(html: String) {
        val doc = Jsoup.parse(html)

        val byLabelText = doc.queryAllByLabelText("Username", selector = "input")

        assertEquals("input", byLabelText.single().tagName())
    }

    // TODO Wrapper labels where the label text is in another child element

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByLabelText("Email")

        assertTrue(byLabelText.isEmpty())
    }

    @Test
    fun `search by regex`() {
        val doc = Jsoup.parse(
            """
            <label for='id1'>
              User id
            </label>
            <input id='id1' />
            <label>
              Username
              <textarea></textarea>
            </label> 
        """
        )

        val byLabelText = doc.queryAllByLabelText("Usern.*".toRegex())

        assertTrue(byLabelText.single().tagName() == "textarea")
    }

    @Test
    fun `search by function`() {
        val doc = Jsoup.parse(
            """
            <label for='id1'>
              User id
            </label>
            <input id='id1' />
            <label>
              Username
              <textarea></textarea>
            </label> 
        """
        )

        val byLabelText = doc.queryAllByLabelText {
            it.contains("name")
        }

        assertTrue(byLabelText.single().tagName() == "textarea")
    }
}
package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnlyCorrectTagsTest {

    @ParameterizedTest
    @ValueSource(strings = ["input", "select", "textarea", "button", "output"])
    fun `get any form element by label`(tag: String) {
        val doc = Jsoup.parse(
            """
                <label for='x'>by for</label>
                <$tag id='x' />
            """
        )

        val queryByLabelText = doc.queryByLabelText("by for")
        val getByLabelText = doc.getByLabelText("by for")
        val queryAllByLabelText = doc.queryAllByLabelText("by for")
        val getAllByLabelText = doc.getAllByLabelText("by for")

        assertEquals(tag, queryByLabelText?.tagName())
        assertEquals(tag, getByLabelText.tagName())
        assertEquals(tag, queryAllByLabelText.single().tagName())
        assertEquals(tag, getAllByLabelText.single().tagName())
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "<label for='id3'>nope</label> <div id='id3'></div>",
            "<label>nope <span /></label>",
            "<label id='x'>nope</label> <section aria-labelledby='x' />"
        ]
    )
    fun `no results with the wrong type`(html: String) {
        val doc = Jsoup.parse(html)

        val queryByLabelText = doc.queryByLabelText("nope")
        val getByLabelText = { doc.getByLabelText("nope") }
        val queryAllByLabelText = doc.queryAllByLabelText("nope")
        val getAllByLabelText = { doc.getAllByLabelText("nope") }

        assertNull(queryByLabelText)
        assertThrows<UndefinedResult> { getByLabelText() }
        assertTrue(queryAllByLabelText.isEmpty())
        assertThrows<UndefinedResult> { getAllByLabelText() }
    }

    // https://testing-playground.com/gist/20925bfef48061b84eacb647acdddc12/c1fe8d48f98eb253fc868ca6ebb66aa9fe0744eb
    @Test
    fun `no results with selector of the wrong type`() {
        val doc = Jsoup.parse(
            """
            <label for='x'>Username</label>
            <div id='x'></input>
        """
        )

        val queryByLabelText = doc.queryByLabelText("Username", selector = "div")
        val getByLabelText = { doc.getByLabelText("Username", selector = "div") }
        val queryAllByLabelText = doc.queryAllByLabelText("Username", selector = "div")
        val getAllByLabelText = { doc.getAllByLabelText("Username", selector = "div") }

        assertNull(queryByLabelText)
        assertThrows<UndefinedResult> { getByLabelText() }
        assertTrue(queryAllByLabelText.isEmpty())
        assertThrows<UndefinedResult> { getAllByLabelText() }
    }
}
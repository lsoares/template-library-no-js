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
            "<label id='x'>nope</label> <section aria-labelledby='x' />",
            "<label for='x'>Username</label><div id='x'></input>",
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
}
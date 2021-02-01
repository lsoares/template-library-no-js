package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NoFormElementsTest {

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
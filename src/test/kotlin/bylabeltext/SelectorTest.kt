package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SelectorTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """
            <label>Username<input /></label>
            <label>Username <textarea></textarea></label> 
        """,
            """
            <label id="username">Username</label>
            <input aria-labelledby="username" />
            <textarea aria-labelledby="username" />
        """,
            """
            <label for="x">Username</label>
            <input id="x" />
            <textarea />
        """
        ]
    )
    fun `specifying a selector`(html: String) {
        val doc = Jsoup.parse(html)

        val getByLabelText = doc.getByLabelText("Username", selector = "input")
        val queryByLabelText = doc.queryByLabelText("Username", selector = "input")
        val getAllByLabelText = doc.getAllByLabelText("Username", selector = "input")
        val queryAllByLabelText = doc.queryAllByLabelText("Username", selector = "input")

        assertEquals("input", getByLabelText.tagName())
        assertEquals("input", queryByLabelText?.tagName())
        assertEquals("input", getAllByLabelText.single().tagName())
        assertEquals("input", queryAllByLabelText.single().tagName())
    }
}
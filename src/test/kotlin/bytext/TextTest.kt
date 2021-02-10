package bytext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TextTest {

    @Test
    fun `by text`() {
        val doc = Jsoup.parse("<span>I accept</span><div></div>")

        val getByText = doc.getByText("I accept")
        val queryByText = doc.queryByText("I accept")
        val getAllByText = doc.getAllByText("I accept")
        val queryAllByText = doc.queryAllByText("I accept")

        assertEquals("span", getByText.tagName())
        assertEquals("span", queryByText?.tagName())
        assertEquals("span", getAllByText.single().tagName())
        assertEquals("span", queryAllByText.single().tagName())
    }

    @Test
    fun `by text is case sensitive`() {
        val doc = Jsoup.parse("<span>I accept</span><div></div>")

        val queryAllByText = doc.queryAllByText("i accept")

        assertTrue(queryAllByText.isEmpty())
    }
}
package bytext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextNotExactTest {

    @Test
    fun `not exact`() {
        val doc = Jsoup.parse("<span>I accept</span><div></div>")

        val getByText = doc.getByText("Accept", exact = false)
        val queryByText = doc.queryByText("Accept", exact = false)
        val getAllByText = doc.getAllByText("Accept", exact = false)
        val queryAllByText = doc.queryAllByText("Accept", exact = false)

        assertEquals("span", getByText.tagName())
        assertEquals("span", queryByText?.tagName())
        assertEquals("span", getAllByText.single().tagName())
        assertEquals("span", queryAllByText.single().tagName())
    }

    @Test
    fun `not exact with selector`() {
        val doc = Jsoup.parse("""
            <div>
                <span>I accept</span>
                <p>I accept</p>
            </div>
            <p>I accept</p>
        """
        )

        val getByText = doc.getByText("Accept", exact = false, selector = "div p")
        val queryByText = doc.queryByText("Accept", exact = false, selector = "div p")
        val getAllByText = doc.getAllByText("Accept", exact = false, selector = "div p")
        val queryAllByText = doc.queryAllByText("Accept", exact = false, selector = "div p")

        assertEquals("p", getByText.tagName())
        assertEquals("p", queryByText?.tagName())
        assertEquals("p", getAllByText.single().tagName())
        assertEquals("p", queryAllByText.single().tagName())
    }
}
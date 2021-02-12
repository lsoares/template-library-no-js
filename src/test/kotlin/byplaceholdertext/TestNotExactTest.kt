package byplaceholdertext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestNotExactTest {

    @Test
    fun `not exact`() {
        val doc = Jsoup.parse("<textarea name='x' placeholder='email address' />")

        val getByPlaceholderText = doc.getByPlaceholderText("email", exact = false)
        val queryByPlaceholderText = doc.queryByPlaceholderText("email", exact = false)
        val getAllByPlaceholderText = doc.getAllByPlaceholderText("email", exact = false)
        val queryAllByPlaceholderText = doc.queryAllByPlaceholderText("email", exact = false)

        assertEquals("textarea", getByPlaceholderText.tagName())
        assertEquals("textarea", queryByPlaceholderText?.tagName())
        assertEquals("textarea", getAllByPlaceholderText.single().tagName())
        assertEquals("textarea", queryAllByPlaceholderText.single().tagName())
    }

    @Test
    fun `not exact with selector`() {
        val doc = Jsoup.parse("""
            <div>
                <textarea placeholder='email address' />
                <input placeholder='email address' />
            </div>
        """
        )

        val getByPlaceholderText = doc.getByPlaceholderText("email", exact = false, selector = "div input")
        val queryByPlaceholderText = doc.queryByPlaceholderText("email", exact = false, selector = "div input")
        val getAllByPlaceholderText = doc.getAllByPlaceholderText("email", exact = false, selector = "div input")
        val queryAllByPlaceholderText = doc.queryAllByPlaceholderText("email", exact = false, selector = "div input")

        assertEquals("input", getByPlaceholderText.tagName())
        assertEquals("input", queryByPlaceholderText?.tagName())
        assertEquals("input", getAllByPlaceholderText.single().tagName())
        assertEquals("input", queryAllByPlaceholderText.single().tagName())
    }
}
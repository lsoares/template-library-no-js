package bytitle

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextNotExactTest {

    @Test
    fun `not exact`() {
        val doc = Jsoup.parse("<img title='abcdef' />")

        val getByTitle = doc.getByTitle("abc", exact = false)
        val queryByTitle = doc.queryByTitle("abc", exact = false)
        val getAllByTitle = doc.getAllByTitle("abc", exact = false)
        val queryAllByTitle = doc.queryAllByTitle("abc", exact = false)

        assertEquals("img", getByTitle.tagName())
        assertEquals("img", queryByTitle?.tagName())
        assertEquals("img", getAllByTitle.single().tagName())
        assertEquals("img", queryAllByTitle.single().tagName())
    }

    @Test
    fun `not exact with selector`() {
        val doc = Jsoup.parse(
            """<img title='abcdef' />
                    <input title='abcdef' />"""
        )

        val getByTitle = doc.getByTitle("abc", exact = false, selector = "input")
        val queryByTitle = doc.queryByTitle("abc", exact = false, selector = "input")
        val getAllByTitle = doc.getAllByTitle("abc", exact = false, selector = "input")
        val queryAllByTitle = doc.queryAllByTitle("abc", exact = false, selector = "input")

        assertEquals("input", getByTitle.tagName())
        assertEquals("input", queryByTitle?.tagName())
        assertEquals("input", getAllByTitle.single().tagName())
        assertEquals("input", queryAllByTitle.single().tagName())
    }
}
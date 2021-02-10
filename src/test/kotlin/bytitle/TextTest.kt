package bytitle

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextTest {

    @Test
    fun `by text`() {
        val doc = Jsoup.parse(
            """<section title="a a a" /> <img title="a b c" />"""
        )

        val getByTitle = doc.getByTitle("a b c")
        val queryByTitle = doc.queryByTitle("a b c")
        val getAllByTitle = doc.getAllByTitle("a b c")
        val queryAllByTitle = doc.queryAllByTitle("a b c")

        assertEquals("img", getByTitle.tagName())
        assertEquals("img", queryByTitle?.tagName())
        assertEquals("img", getAllByTitle.single().tagName())
        assertEquals("img", queryAllByTitle.single().tagName())
    }
}
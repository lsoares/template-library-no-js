package bytitle

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegexTest {

    @Test
    fun `search one by regex`() {
        val doc = Jsoup.parse(
            """<section title="abc" /> <img title="bca" />"""
        )

        val queryByTitle = doc.queryByTitle("ab.*".toRegex())
        val getByTitle = doc.getByTitle("ab.*".toRegex())
        val queryAllByTitle = doc.queryAllByTitle("ab.*".toRegex())
        val getAllByTitle = doc.getAllByTitle("ab.*".toRegex())

        assertEquals("section", queryByTitle?.tagName())
        assertEquals("section", getByTitle.tagName())
        assertEquals("section", queryAllByTitle.single().tagName())
        assertEquals("section", getAllByTitle.single().tagName())
    }

    @Test
    fun `search one by regex with a selector`() {
        val doc = Jsoup.parse(
            """<section title="abc" /> <div title="abc" />"""
        )

        val queryByTitle = doc.queryByTitle("ab.*".toRegex(), selector = "div")
        val getByTitle = doc.getByTitle("ab.*".toRegex(), selector = "div")
        val queryAllByTitle = doc.queryAllByTitle("ab.*".toRegex(), selector = "div")
        val getAllByTitle = doc.getAllByTitle("ab.*".toRegex(), selector = "div")

        assertEquals("div", queryByTitle?.tagName())
        assertEquals("div", getByTitle.tagName())
        assertEquals("div", queryAllByTitle.single().tagName())
        assertEquals("div", getAllByTitle.single().tagName())
    }
}
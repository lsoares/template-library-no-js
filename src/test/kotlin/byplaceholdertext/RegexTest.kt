package byplaceholdertext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegexTest {

    @Test
    fun `search one by regex`() {
        val doc = Jsoup.parse(
            """<textarea name='x' placeholder='place x' />
                    <textarea name='y' placeholder='place y' />"""
        )

        val queryByPlaceholderText = doc.queryByPlaceholderText(" y".toRegex())
        val getByPlaceholderText = doc.getByPlaceholderText(" y".toRegex())
        val queryAllByPlaceholderText = doc.queryAllByPlaceholderText(" y".toRegex())
        val getAllByPlaceholderText = doc.getAllByPlaceholderText(" y".toRegex())

        assertEquals("y", queryByPlaceholderText?.attr("name"))
        assertEquals("y", getByPlaceholderText.attr("name"))
        assertEquals("y", queryAllByPlaceholderText.single().attr("name"))
        assertEquals("y", getAllByPlaceholderText.single().attr("name"))
    }

    @Test
    fun `search one by regex with a selector`() {
        val doc = Jsoup.parse(
            """<textarea name='x' placeholder='place x' />
                    <textarea name='y' placeholder='place y' />
                    <input name='z' placeholder='place y' />"""
        )

        val queryByPlaceholderText = doc.queryByPlaceholderText(" y".toRegex(), selector = "textarea")
        val getByPlaceholderText = doc.getByPlaceholderText(" y".toRegex(), selector = "textarea")
        val queryAllByPlaceholderText = doc.queryAllByPlaceholderText(" y".toRegex(), selector = "textarea")
        val getAllByPlaceholderText = doc.getAllByPlaceholderText(" y".toRegex(), selector = "textarea")

        assertEquals("y", queryByPlaceholderText?.attr("name"))
        assertEquals("y", getByPlaceholderText.attr("name"))
        assertEquals("y", queryAllByPlaceholderText.single().attr("name"))
        assertEquals("y", getAllByPlaceholderText.single().attr("name"))
    }
}
package byplaceholdertext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextTest {

    @Test
    fun `by text`() {
        val doc = Jsoup.parse(
            """<textarea name='x' placeholder='place x' />
                    <textarea name='y' placeholder='place y' />"""
        )

        val getByPlaceholderText = doc.getByPlaceholderText("place y")
        val queryByPlaceholderText = doc.queryByPlaceholderText("place y")
        val getAllByPlaceholderText = doc.getAllByPlaceholderText("place y")
        val queryAllByPlaceholderText = doc.queryAllByPlaceholderText("place y")

        assertEquals("y", getByPlaceholderText.attr("name"))
        assertEquals("y", queryByPlaceholderText?.attr("name"))
        assertEquals("y", getAllByPlaceholderText.single().attr("name"))
        assertEquals("y", queryAllByPlaceholderText.single().attr("name"))
    }
}
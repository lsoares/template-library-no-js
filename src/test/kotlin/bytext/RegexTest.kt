package bytext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegexTest {

    @Test
    fun `search one by regex`() {
        val doc = Jsoup.parse("<span>user</span><div>Username</div>")

        val queryByText = doc.queryByText("Usern.*".toRegex())
        val getByText = doc.getByText("Usern.*".toRegex())
        val queryAllByText = doc.queryAllByText("Usern.*".toRegex())
        val getAllByText = doc.getAllByText("Usern.*".toRegex())

        assertEquals("div", queryByText?.tagName())
        assertEquals("div", getByText.tagName())
        assertEquals("div", queryAllByText.single().tagName())
        assertEquals("div", getAllByText.single().tagName())
    }

    @Test
    fun `search one by regex with a selector`() {
        val doc = Jsoup.parse("<span>user</span><div>Username</div>")

        val queryByText = doc.queryByText("Usern.*".toRegex(), selector = "div")
        val getByText = doc.getByText("Usern.*".toRegex(), selector = "div")
        val queryAllByText = doc.queryAllByText("Usern.*".toRegex(), selector = "div")
        val getAllByText = doc.getAllByText("Usern.*".toRegex(), selector = "div")

        assertEquals("div", queryByText?.tagName())
        assertEquals("div", getByText.tagName())
        assertEquals("div", queryAllByText.single().tagName())
        assertEquals("div", getAllByText.single().tagName())
    }
}
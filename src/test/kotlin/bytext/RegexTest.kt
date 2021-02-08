package bytext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RegexTest {

    @Test
    fun `search one by regex`() {
        val doc = Jsoup.parse("<span>user</span><div>Username</div>")

        val queryByText = doc.queryByText("Usern.*".toRegex())
        val getByText = doc.getByText("Usern.*".toRegex())
        val queryAllByText = doc.queryAllByText("Usern.*".toRegex())
        val getAllByText = doc.getAllByText("Usern.*".toRegex())

        assertTrue(queryByText?.tagName() == "div")
        assertTrue(getByText.tagName() == "div")
        assertTrue(queryAllByText.single().tagName() == "div")
        assertTrue(getAllByText.single().tagName() == "div")
    }

    @Test
    fun `search one by regex with a selector`() {
        val doc = Jsoup.parse("<span>user</span><div>Username</div>")

        val queryByText = doc.queryByText("Usern.*".toRegex(), selector = "div")
        val getByText = doc.getByText("Usern.*".toRegex(), selector = "div")
        val queryAllByText = doc.queryAllByText("Usern.*".toRegex(), selector = "div")
        val getAllByText = doc.getAllByText("Usern.*".toRegex(), selector = "div")

        assertTrue(queryByText?.tagName() == "div")
        assertTrue(getByText.tagName() == "div")
        assertTrue(queryAllByText.single().tagName() == "div")
        assertTrue(getAllByText.single().tagName() == "div")
    }
}
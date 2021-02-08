package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegexTest {

    @Test
    fun `search one by regex`() {
        val doc = Jsoup.parse(
            """
            <label for='id1'>User id</label>
            <input id='id1' />
            <label>Username<textarea></textarea></label>
        """
        )

        val queryByLabelText = doc.queryByLabelText("Usern.*".toRegex())
        val getByLabelText = doc.getByLabelText("Usern.*".toRegex())
        val queryAllByLabelText = doc.queryAllByLabelText("Usern.*".toRegex())
        val getAllByLabelText = doc.getAllByLabelText("Usern.*".toRegex())

        assertEquals("textarea", queryByLabelText?.tagName())
        assertEquals("textarea", getByLabelText.tagName())
        assertEquals("textarea", queryAllByLabelText.single().tagName())
        assertEquals("textarea", getAllByLabelText.single().tagName())
    }

    @Test
    fun `search one by regex with a selector`() {
        val doc = Jsoup.parse(
            """
            <label for='id1'>User id</label>
            <input id='id1' />
            <label>Username <textarea></textarea></label>
            <label>Username <input/></label>
        """
        )

        val queryByLabelText = doc.queryByLabelText("Usern.*".toRegex(), selector = "textarea")
        val getByLabelText = doc.getByLabelText("Usern.*".toRegex(), selector = "textarea")
        val queryAllByLabelText = doc.queryAllByLabelText("Usern.*".toRegex(), selector = "textarea")
        val getAllByLabelText = doc.getAllByLabelText("Usern.*".toRegex(), selector = "textarea")

        assertEquals("textarea", queryByLabelText?.tagName())
        assertEquals("textarea", getByLabelText.tagName())
        assertEquals("textarea", queryAllByLabelText.single().tagName())
        assertEquals("textarea", getAllByLabelText.single().tagName())
    }
}
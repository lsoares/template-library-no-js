package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Wrapped {

    @Test
    fun `get wrapped - one`() {
        val doc = Jsoup.parse(
            """
             <label>Username <input name="username" /></label>
             <label>Password <input /></label>
           """
        )

        val queryByLabelText = doc.queryByLabelText("Username")
        val getByLabelText = doc.getByLabelText("Username")

        assertEquals("username", queryByLabelText?.attr("name"))
        assertEquals("username", getByLabelText.attr("name"))
    }

    @Test
    fun `get wrapped - all`() {
        val doc = Jsoup.parse(
            """
             <label>Username <input name="username" /></label>
             <label>Password <input /></label>
           """
        )

        val queryAllByLabelText = doc.queryAllByLabelText("Username")
        val getAllByLabelText = doc.getAllByLabelText("Username")

        assertEquals("username", queryAllByLabelText.single().attr("name"))
        assertEquals("username", getAllByLabelText.single().attr("name"))
    }
}
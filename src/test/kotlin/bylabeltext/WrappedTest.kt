package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WrappedTest {

    @Test
    fun `get wrapped`() {
        val doc = Jsoup.parse(
            """
             <label>Username <input name="username" /></label>
             <label>Password <input /></label>
           """
        )

        val queryByLabelText = doc.queryByLabelText("Username")
        val getByLabelText = doc.getByLabelText("Username")
        val queryAllByLabelText = doc.queryAllByLabelText(text = "Username")
        val getAllByLabelText = doc.getAllByLabelText("Username")

        assertEquals("username", queryByLabelText?.attr("name"))
        assertEquals("username", getByLabelText.attr("name"))
        assertEquals("username", queryAllByLabelText.single().attr("name"))
        assertEquals("username", getAllByLabelText.single().attr("name"))
    }
}
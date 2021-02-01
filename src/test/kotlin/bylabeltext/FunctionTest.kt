package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FunctionTest {

    @Test
    fun `search by function`() {
        val doc = Jsoup.parse(
            """
            <label for='id1'>
              User id
            </label>
            <input id='id1' />
            <label>
              Username
              <textarea></textarea>
            </label> 
        """
        )

        val getByLabelText = doc.getByLabelText { it.text().contains("name") }
        val queryByLabelText = doc.queryByLabelText { it.text().contains("name") }
        val getAllByLabelText = doc.getAllByLabelText { it.text().contains("name") }
        val queryAllByLabelText = doc.queryAllByLabelText { it.text().contains("name") }

        assertEquals("textarea", getByLabelText.tagName())
        assertEquals("textarea", queryByLabelText?.tagName())
        assertEquals("textarea", getAllByLabelText.single().tagName())
        assertEquals("textarea", queryAllByLabelText.single().tagName())
    }

    // TODO: combine with selector
}
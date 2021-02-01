package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class QueryAllByLabelTextTest {

    // TODO Wrapper labels where the label text is in another child element

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

        val byLabelText = doc.queryAllByLabelText {
            it.text().contains("name")
        }

        assertEquals("textarea", byLabelText.single().tagName())
    }
}
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class QueryAllByLabelTextTest {

    @Test
    fun `returns empty array when no results match`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = doc.queryAllByLabelText("Email")

        assertTrue(byLabelText.isEmpty())
    }


    @Test
    fun `search by regex`() {
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

        val byLabelText = doc.queryAllByLabelText(textRegex = "User.*".toRegex())

        assertEquals(
            """[<input id="id1">, <textarea></textarea>]""",
            byLabelText.toString()
        )
    }
}
package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
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

        assertTrue(queryByLabelText?.tagName() == "textarea")
        assertTrue(getByLabelText.tagName() == "textarea")
        assertTrue(queryAllByLabelText.single().tagName() == "textarea")
        assertTrue(getAllByLabelText.single().tagName() == "textarea")
    }

    // TODO: combine with selector
}
package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NotExactTest {

    @Test
    fun `one by label - for - NOT exact`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
        """
        )

        val queryByLabelText = doc.queryByLabelText("email", exact = false)
        val getByLabelText = doc.getByLabelText("email", exact = false)

        assertEquals("email", queryByLabelText?.id())
        assertEquals("email", getByLabelText.id())
    }

    @Test
    fun `all by label - for - NOT exact`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
        """
        )

        val queryAllByLabelText = doc.queryAllByLabelText("email", exact = false)
        val getAllByLabelText = doc.getAllByLabelText("email", exact = false)

        assertEquals("email", queryAllByLabelText.single().id())
        assertEquals("email", getAllByLabelText.single().id())
    }
}
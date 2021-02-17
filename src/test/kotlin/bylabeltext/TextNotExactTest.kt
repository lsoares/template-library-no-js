package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextNotExactTest {

    @Test
    fun `not exact`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
        """
        )

        val getByLabelText = doc.getByLabelText("email", exact = false)
        val queryByLabelText = doc.queryByLabelText("email", exact = false)
        val getAllByLabelText = doc.getAllByLabelText("email", exact = false)
        val queryAllByLabelText = doc.queryAllByLabelText("email", exact = false)

        assertEquals("email", getByLabelText.id())
        assertEquals("email", queryByLabelText?.id())
        assertEquals("email", getAllByLabelText.single().id())
        assertEquals("email", queryAllByLabelText.single().id())
    }

    @Test
    fun `not exact with selector`() {
        val doc = Jsoup.parse(
            """
                <div>
                    <label for="email">Email address</label>
                    <input id="email" />
                    <label for="email2">Email address</label>
                    <textarea id="email2" />
                </div>
                """
        )

        val getByLabelText = doc.getByLabelText("email", exact = false, selector = "div input")
        val queryByLabelText = doc.queryByLabelText("email", exact = false, selector = "div input")
        val getAllByLabelText = doc.getAllByLabelText("email", exact = false, selector = "div input")
        val queryAllByLabelText = doc.queryAllByLabelText("email", exact = false, selector = "div input")

        assertEquals("email", getByLabelText.id())
        assertEquals("email", queryByLabelText?.id())
        assertEquals("email", getAllByLabelText.single().id())
        assertEquals("email", queryAllByLabelText.single().id())
    }
}
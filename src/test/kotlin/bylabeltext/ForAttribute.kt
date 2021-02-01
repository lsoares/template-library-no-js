package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ForAttribute {

    @Test
    fun `for with one`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
            <label for="password">Password</label>
            <input id="password" />
        """
        )

        val queryByLabelText = doc.queryByLabelText("Email address")
        val getByLabelText = doc.getByLabelText("Email address")

        assertEquals("email", queryByLabelText?.id())
        assertEquals("email", getByLabelText.id())
    }

    @Test
    fun `for with all`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
            <label for="password">Password</label>
            <input id="password" />
        """
        )

        val queryAllByLabelText = doc.queryAllByLabelText("Email address")
        val getAllByLabelText = doc.getAllByLabelText("Email address")

        assertEquals("email", queryAllByLabelText.single().id())
        assertEquals("email", getAllByLabelText.single().id())
    }
}
package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ForAttributeTest {

    @Test
    fun `for attribute`() {
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
        val queryAllByLabelText = doc.queryAllByLabelText("Email address")
        val getAllByLabelText = doc.getAllByLabelText("Email address")

        assertEquals("email", queryByLabelText?.id())
        assertEquals("email", getByLabelText.id())
        assertEquals("email", queryAllByLabelText.single().id())
        assertEquals("email", getAllByLabelText.single().id())
    }
}
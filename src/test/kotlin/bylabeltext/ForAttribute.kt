package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ForAttribute {

    @Test
    fun `label for`() {
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
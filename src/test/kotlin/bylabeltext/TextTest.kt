package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TextTest {

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

    @Test
    fun `by aria labelled by`() {
        val doc = Jsoup.parse(
            """
            <label id="username-label">Username</label>
            <input aria-labelledby="username-label" />
        """
        )

        val queryByLabelText = doc.queryByLabelText("Username")
        val getByLabelText = doc.getByLabelText("Username")
        val queryAllByLabelText = doc.queryAllByLabelText("Username")
        val getAllByLabelText = doc.getAllByLabelText("Username")

        assertEquals("input", queryByLabelText?.tagName())
        assertEquals("input", getByLabelText.tagName())
        assertEquals("input", getAllByLabelText.single().tagName())
        assertEquals("input", queryAllByLabelText.single().tagName())
    }

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
        val queryAllByLabelText = doc.queryAllByLabelText("Username")
        val getAllByLabelText = doc.getAllByLabelText("Username")

        assertEquals("username", queryByLabelText?.attr("name"))
        assertEquals("username", getByLabelText.attr("name"))
        assertEquals("username", queryAllByLabelText.single().attr("name"))
        assertEquals("username", getAllByLabelText.single().attr("name"))
    }
}
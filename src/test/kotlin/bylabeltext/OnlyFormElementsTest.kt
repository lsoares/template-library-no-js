package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnlyFormElementsTest {

    @Test
    fun `only form elements are returned in one`() {
        val doc = Jsoup.parse(
            """
            <label for="email1">Email address</label>
            <input id="email1" />
            <label>Email address<section /></label>
        """
        )

        val getByLabelText = doc.getByLabelText("Email address")
        val queryByLabelText = doc.queryByLabelText("Email address")

        assertEquals("input", queryByLabelText?.tagName())
        assertEquals("input", getByLabelText.tagName())
    }

    @Test
    fun `only form elements are returned in all`() {
        val doc = Jsoup.parse(
            """
            <label for="email1">Email address</label>
            <input id="email1" />
            <label>Email address<section /></label>
        """
        )

        val getAllByLabelText = doc.getAllByLabelText("Email address")
        val queryAllByLabelText = doc.queryAllByLabelText("Email address")

        assertEquals("input", getAllByLabelText.single().tagName())
        assertEquals("input", queryAllByLabelText.single().tagName())
    }

    @ParameterizedTest
    @ValueSource(strings = ["input", "select", "textarea", "button", "output"])
    fun `get any form element by label in one`(tag: String) {
        val doc = Jsoup.parse(
            """
                <label for='x'>by for</label>
                <$tag id='x' />
            """
        )

        val queryByLabelText = doc.queryByLabelText("by for")
        val getByLabelText = doc.getByLabelText("by for")

        assertEquals(tag, queryByLabelText?.tagName())
        assertEquals(tag, getByLabelText.tagName())
    }

    @ParameterizedTest
    @ValueSource(strings = ["input", "select", "textarea", "button", "output"])
    fun `get any form element by label in all`(tag: String) {
        val doc = Jsoup.parse(
            """
                <label for='x'>by for</label>
                <$tag id='x' />
            """
        )

        val queryAllByLabelText = doc.queryAllByLabelText("by for")
        val getAllByLabelText = doc.getAllByLabelText("by for")

        assertEquals(tag, queryAllByLabelText.single().tagName())
        assertEquals(tag, getAllByLabelText.single().tagName())
    }
}
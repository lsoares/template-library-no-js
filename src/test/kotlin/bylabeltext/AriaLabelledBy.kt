package bylabeltext

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AriaLabelledBy {

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
        val queryAllByLabelText = doc.queryAllByLabelText(text = "Username")
        val getAllByLabelText = doc.getAllByLabelText("Username")

        assertEquals("input", queryByLabelText?.tagName())
        assertEquals("input", getByLabelText.tagName())
        assertEquals("input", getAllByLabelText.single().tagName())
        assertEquals("input", queryAllByLabelText.single().tagName())
    }
}
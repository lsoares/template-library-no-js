package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetByLabelTextTest {

    @Test
    fun `fails when more than one is available`() {
        val doc = Jsoup.parse(
            """
            <label for="x">Username <input /></label>
            <input id="x" />
        """
        )

        val byLabelText = { doc.getByLabelText("Username") }

        assertThrows(UndefinedResult::class.java) { byLabelText() }
    }

    @Test
    fun `fails when none is available`() {
        val doc = Jsoup.parse("<span></span>")

        val byLabelText = { doc.getByLabelText("Username") }

        assertThrows(UndefinedResult::class.java) { byLabelText() }
    }
    // TODO: test correct type
    // TODO: receive function
    // TODO: non-exact match
}


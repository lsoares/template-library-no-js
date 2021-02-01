package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnexpectedMultipleTest {

    @Test
    fun `fails when more than one is available`() {
        val doc = Jsoup.parse(
            """
            <label for="x">Username <input /></label>
            <input id="x" />
        """
        )

        val getByLabelText = { doc.getByLabelText("Username") }
        val queryByLabelText = { doc.queryByLabelText("Username") }

        assertThrows(UndefinedResult::class.java) { getByLabelText() }
        assertThrows(UndefinedResult::class.java) { queryByLabelText() }
    }
}


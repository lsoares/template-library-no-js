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
            <label for="email1">Email</label>
            <input id="email1" />
            <label>Email<input id="email2" /></label>
        """
        )

        val getByLabelText = { doc.getByLabelText("Email") }
        val queryByLabelText = { doc.queryByLabelText("Email") }

        assertThrows(UndefinedResult::class.java) { getByLabelText() }
        assertThrows(UndefinedResult::class.java) { queryByLabelText() }
    }
}


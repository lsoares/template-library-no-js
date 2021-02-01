package bylabeltext

import UndefinedResult
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class GetAllByLabelTextTest {

    @Test
    fun `fails when no results are found`() {
        val doc = Jsoup.parse("<span />")

        val byLabelText = { doc.getAllByLabelText("Email") }

        assertThrows(UndefinedResult::class.java) { byLabelText() }
    }

    // TODO: receive function
}
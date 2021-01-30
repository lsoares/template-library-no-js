import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetByLabelTextTest {

    @Test
    fun `get by label - for`() {
        val doc = Jsoup.parse(
            """
            <label for="email">Email address</label>
            <input id="email" />
            <label for="password">Password</label>
            <input id="password" />
        """
        )

        val byLabelText = doc.getByLabelText("Email address")

        assertEquals("""<input id="email">""", byLabelText.toString())
    }

    @Test
    fun `get by label - wrapper`() {
        val doc = Jsoup.parse(
            """
            <label>Username <input id="username" /></label>
            <label>Password <input /></label>
        """
        )

        val byLabelText = doc.getByLabelText("Username")

        assertEquals("""<input id="username">""", byLabelText.toString())
    }

    // todo: filter only form elements
//<input>
//<label>
//<select>
//<textarea>
//<button>
//<fieldset>
//<legend>
//<datalist>
//<output>
//<option>
//<optgroup>

}


package bydisplayvalue

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TextNotExactTest {

    @ParameterizedTest
    @ValueSource(strings = [
        "<input value='Luis Soares' />",
        "<textarea>Luis Soares</textarea>",
        """<select>
                  <option selected='selected'>Luis Soares</option>
               </select>""",
        """<select>
                  <option selected>Luis Soares</option>
               </select>""",
    ])
    fun `not exact`(html: String) {
        val doc = Jsoup.parse(html)

        val getByDisplayValue = doc.getByDisplayValue("luis", exact = false)
        val queryByDisplayValue = doc.queryByDisplayValue("luis", exact = false)
        val getAllByDisplayValue = doc.getAllByDisplayValue("luis", exact = false)
        val queryAllByDisplayValue = doc.queryAllByDisplayValue("luis", exact = false)

        assertNotNull(getByDisplayValue)
        assertNotNull(queryByDisplayValue)
        assertNotNull(getAllByDisplayValue.single())
        assertNotNull(queryAllByDisplayValue.single())
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "<input value='Luis Soares' />",
        "<textarea>Luis Soares</textarea>",
        """<select>
                  <option selected='selected'>Luis Soares</option>
               </select>""",
        """<select>
                  <option selected>Luis Soares</option>
               </select>""",
    ])
    fun `not exact with selector`(html: String) {
        val doc = Jsoup.parse("<div>$html</div>$html")

        val getByDisplayValue = doc.getByDisplayValue("luis", exact = false, selector = "div > *")
        val queryByDisplayValue = doc.queryByDisplayValue("luis", exact = false, selector = "div > *")
        val getAllByDisplayValue = doc.getAllByDisplayValue("luis", exact = false, selector = "div > *")
        val queryAllByDisplayValue = doc.queryAllByDisplayValue("luis", exact = false, selector = "div > *")

        assertNotNull(getByDisplayValue)
        assertNotNull(queryByDisplayValue)
        assertNotNull(getAllByDisplayValue.single())
        assertNotNull(queryAllByDisplayValue.single())
    }
}
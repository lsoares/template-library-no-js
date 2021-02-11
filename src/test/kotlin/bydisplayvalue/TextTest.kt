package bydisplayvalue

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TextTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """
                <input name="x" value='A b c' />
                <input value='b c' />""",
            """
                <textarea name="x">A b c</textarea>
                <textarea>b c</textarea>""",
            """
               <select>
                   <option selected='selected'>wrong</option>
               </select>
               <select name="x">
                  <option selected='selected'>A b c</option>
               </select>""",
            """
               <select name="x">
                  <option selected>A b c</option>
               </select>""",
        ]
    )
    fun `by text`(html: String) {
        val doc = Jsoup.parse(html)

        val getByDisplayValue = doc.getByDisplayValue("A b c")
        val queryByDisplayValue = doc.queryByDisplayValue("A b c")
        val getAllByDisplayValue = doc.getAllByDisplayValue("A b c")
        val queryAllByDisplayValue = doc.queryAllByDisplayValue("A b c")

        assertEquals("x", getByDisplayValue.attr("name"))
        assertEquals("x", queryByDisplayValue?.attr("name"))
        assertEquals("x", getAllByDisplayValue.single().attr("name"))
        assertEquals("x", queryAllByDisplayValue.single().attr("name"))
    }
}
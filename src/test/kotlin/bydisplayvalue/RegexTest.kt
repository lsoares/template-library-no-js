package bydisplayvalue

import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RegexTest {

    @ParameterizedTest
    @ValueSource(strings = [
        """
           <input value="benjamin franklin" id="name" />""",
        """
           <textarea id="name">benjamin franklin</textarea>
           <textarea>frank</textarea>""",
        """
           <select>
              <option selected='selected'>wrong</option>
           </select>
           <select id="name">
              <option selected='selected'>benjamin franklin</option>
           </select>"""
    ])
    fun `search input by regex`(html: String) {
        val doc = Jsoup.parse(html)

        val queryByDisplayValue = doc.queryByDisplayValue("benj.*".toRegex())
        val getByDisplayValue = doc.getByDisplayValue("benj.*".toRegex())
        val queryAllByDisplayValue = doc.queryAllByDisplayValue("benj.*".toRegex())
        val getAllByDisplayValue = doc.getAllByDisplayValue("benj.*".toRegex())

        assertEquals("name", queryByDisplayValue?.id())
        assertEquals("name", getByDisplayValue.id())
        assertEquals("name", queryAllByDisplayValue.single().id())
        assertEquals("name", getAllByDisplayValue.single().id())
    }

    @Test
    fun `search one by regex with a selector`() {
        val doc = Jsoup.parse("""
            <div class='wrapper'>
               <input value="benjamin franklin" id="name" />
               <textarea>benjamin franklin</textarea>
               <select>
                  <option selected='true'>benjamin franklin</option>
               </select>
            </div>
        """)

        val queryByDisplayValue = doc.queryByDisplayValue("benj.*".toRegex(), selector = ".wrapper input")
        val getByDisplayValue = doc.getByDisplayValue("benj.*".toRegex(), selector = ".wrapper input")
        val queryAllByDisplayValue = doc.queryAllByDisplayValue("benj.*".toRegex(), selector = ".wrapper input")
        val getAllByDisplayValue = doc.getAllByDisplayValue("benj.*".toRegex(), selector = ".wrapper input")

        assertEquals("name", queryByDisplayValue?.id())
        assertEquals("name", getByDisplayValue.id())
        assertEquals("name", queryAllByDisplayValue.single().id())
        assertEquals("name", getAllByDisplayValue.single().id())
    }
}
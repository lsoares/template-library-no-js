package bytext

import getBy
import org.jsoup.nodes.Element

fun Element.getByText(text: String): Element =
    queryAllByText(text = text).getBy()

package bytext

import org.jsoup.nodes.Element
import queryBy

fun Element.queryByText(text: String) =
    queryAllByText(text).queryBy()
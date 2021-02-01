import org.jsoup.nodes.Element
import java.lang.Exception

internal fun List<Element>.getBy() =
    singleOrNull() ?: throw UndefinedResult()

internal fun List<Element>.getAllBy() =
    takeIf { it.isNotEmpty() } ?: throw UndefinedResult()

internal fun List<Element>.queryBy() =
    also { check(it.size <= 1) { throw UndefinedResult() } }
        .firstOrNull()

class UndefinedResult : Exception()
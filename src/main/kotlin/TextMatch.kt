interface TextMatch

class ByString(val value: String) : TextMatch
class ByRegex(val value: Regex) : TextMatch
class ByFunction(val matcher: (String) -> Boolean): TextMatch
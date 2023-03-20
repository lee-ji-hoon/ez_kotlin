package book.effective.chapter01.item06

sealed class CustomException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause) {

    object JSONParseException : CustomException("JSON Parsing 도중 문제가 생겼습니다.")
}
package example.etc

inline fun <reified R> CharSequence?.ifNullOrBlank(block: () -> R): R {
    return if (isNullOrBlank()) block() else this as R
}

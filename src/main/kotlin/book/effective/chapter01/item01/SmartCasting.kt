package book.effective.chapter01.item01

private val name: String? = "Marton"
private val surname: String = "Braun"

val fullName: String?
    get() = name?.let { "$it $surname" }

val fullName2: String? = name?.let { "$it $surname`" }

fun main() {
    if (fullName != null) {
        println(fullName?.length)
    }

    if (fullName2 != null) {
        println(fullName2.length)
    }
}

package book.effective.chapter01.item01

fun main() {
    val list = listOf(1, 2, 3)

    println(list.javaClass::class)

    if (list is MutableList) {
        list.add(4)
    }
}
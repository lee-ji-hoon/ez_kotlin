package book.effective.chapter01.item12


fun Int.factorial(): Int = (1..this).product()

fun Iterable<Int>.product(): Int = fold(1) { acc, i -> acc * i }

operator fun Int.not() = factorial()

operator fun Int.times(operation: () -> Unit): () -> Unit = { repeat(this) { operation() } }

infix fun Int.timesRepeated(operation: () -> Unit): () -> Unit = { repeat(this) { operation() } }

fun main() {
    println(10 * 6.factorial())
    println(10 * !6)

    val tripleHello = 3 * { println("Hello") }
    tripleHello()

    val tripleHelloInfix = 3 timesRepeated { println("Hello") }
    tripleHelloInfix()
}
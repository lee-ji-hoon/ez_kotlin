package book.effective

import sun.misc.VM
import kotlin.random.Random
import kotlin.system.measureNanoTime


fun main() {

    val accessATime = measureNanoTime {
        accessA()
    }
    println("accessA : $accessATime")
    val createATime = measureNanoTime {
        createA()
    }
    println("createA : $createATime")
    val createListAccessATime = measureNanoTime {
        createListAccessA()
    }
    println("createListAccessA : $createListAccessATime")
    val createListCreateATime = measureNanoTime {
        createListCreateA()
    }
    println("createListCreateA : $createListCreateATime")
}


data class A(
    val name: String = "",
    val id: Int = Random(10_000_000).nextInt()
)


private val a = A()
// 1208ns
fun accessA() {
    a
}
// 1333ns
fun createA() {
    A()
}
// 276042ns
fun createListAccessA() {
    List(1000) { a }
}
// 301083ns
fun createListCreateA() {
    List(1000) { A() }
}
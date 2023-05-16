package book.effective.chapter03.item24

class Cup<in T>
open class Dog
class Puppy : Dog()

fun main() {
//    val a: Cup<Dog> = Cup<Puppy>()
    val b: Cup<Puppy> = Cup<Dog>()
//    val anys: Cup<Any> = Cup<Int>()
    val nothings: Cup<Nothing> = Cup<Int>()
}

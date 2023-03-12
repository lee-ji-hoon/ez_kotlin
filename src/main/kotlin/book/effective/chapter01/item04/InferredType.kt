package book.effective.chapter01.item04


open class Animal
class Zebra : Animal()

fun main() {
//    var animal = Zebra() -> 아래에서 Type mismatch 오류 발생
    var animal: Animal = Zebra() // 명시적으로 지정해서 문제 해결
    animal = Animal()
}
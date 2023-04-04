package book.effective.chapter01.item08

import kotlin.properties.Delegates
import kotlin.reflect.full.primaryConstructor

class Printer(
    val message: String? = "안녕하세요"
) {

    fun print() {
        println(this::class.primaryConstructor)
    }
}

private var printer: Printer? = null
fun main() {
    printer?.print()
    if (printer != null) {
        // 아래 스마트 캐스팅이 될까?
        // printer는 var이다.
        // var의 경우 언제든지 런타임에 바뀔 수 있는 객체이기에 스마트 캐스팅이 되지 않는다.
        // 이것을 항상 유의하고 스마트 캐스팅 쓰자.
        // printer.print()
    }
    val printerSample = printer
    printerSample ?: return // Ealry Return으로 인해 아래부터는 스마트 캐스팅으로 진행
    printerSample.print() // printerSample은 val 값이기에 스마트 캐스팅이 된 모습이다.
}

private var doctorId: Int by Delegates.notNull()
private lateinit var doctorName: String
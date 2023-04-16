package example.etc

import java.lang.StringBuilder
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
@OptIn(ExperimentalTime::class)
fun main() {

    val measuredStringBuilder = measureTime {
        plusStringBuilder()
    }

    val measuredString = measureTime {
        plusString()
    }

    println("String 결과 -> $measuredString") // 3.208us
    println("StringBuilder 결과 -> $measuredStringBuilder") // 1.405125ms
}

fun plusString() {
    var string = ""

    string += "가나다"
    string += "가나다"
    string += "가나다"
    string += "가나다"
    string += "가나다"
    string += "가나다"
}

fun plusStringBuilder() {
    val sb = StringBuilder()
    sb.appendLine("가나다")
    sb.appendLine("가나다")
    sb.appendLine("가나다")
    sb.appendLine("가나다")
    sb.appendLine("가나다")
    sb.appendLine("가나다")
    sb.toString()
}

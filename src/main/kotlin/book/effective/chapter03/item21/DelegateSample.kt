package book.effective.chapter03.item21

import kotlin.reflect.KProperty

fun main() {
    var token: String by LoggingProperty("")
    var attempts: Int by LoggingProperty(0)

    repeat(3) {
        println(token)
        token = "새로운 값 $it"
    }
}

private class LoggingProperty<T>(
    var value: T
) {
    operator fun getValue(
        thisRef: Any?,
        prop: KProperty<*>
    ): T {
        println("${prop.name} returned value $value")
        return value
    }

    operator fun setValue(
        thisRef: Any?,
        prop: KProperty<*>,
        newValue: T
    ) {
        val name = prop.name
        println("$name changed from $value to $newValue")
        value = newValue
    }
}
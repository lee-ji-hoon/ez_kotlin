package book.effective.chapter01.item03

fun main() {
    statedType()
    platformType()
}

fun statedType() {
    val values: String = NullableNotNull().value  // NPE 발생
    println(values.length)
}

fun platformType() {
    // 플랫폼 타입의 경우 사용하는 곳에서 NPE가 발생한다.
    // 그 이유는 타입이 플랫폼 타입(String!) 이기 때문에 값을 사용할 때 NPE가 발생하는 것이다.
    // 그렇기에 한두 번 안전하게 사용했더라도 이후에 다른 사람이 사용할 때는 문제가 생길수도 있다.
    val values = NullableNotNull().value
    println(values.length)  // NPE 발생
}
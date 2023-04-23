
## 연산자 오버로드를 할 때는 의미에 맞게 사용하라.

```kotlin
fun Int.factorial(): Int = (1..this).product()

fun Iterable<Int>.product(): Int = fold(1) { acc, i -> acc * i }

operator fun Int.not() = factorial()

fun main() {
    println(10 * 6.factorial())
    println(10 * !6)
}
```

팩토리얼은 보통 !number (6!) 이런식으로 작성을 한다.

kotlin에서도 `!` 연산자가 존재하는데 해당 연산자는 not() 이다 즉 부정연산자다.   

해당 연산자를 오버로드해서 위에 코드처럼 구현했을 때 우리가 흔히 알고 있던 연산자와 다르게 된다.

### 함수를 N번 호출되길 원할 때 확장함수를 만들어보자.
```kotlin
operator fun Int.times(operation: () -> Unit): () -> Unit = { repeat(this) { operation() } }

infix fun Int.timesRepeated(operation: () -> Unit): () -> Unit = { repeat(this) { operation() } }

fun main() {
    val tripleHello = 3 * { println("Hello") }
    tripleHello()

    val tripleHelloInfix = 3 timesRepeated { println("Hello") }
    tripleHelloInfix()
}
```

- 의미가 명확하지 않을 때는 infix를 활용한 확장 함수도 추천한다.
- 톱레벨 함수를 사용하는 것도 좋지만 이런 함수들은 대부분 구현이 돼있다. (repeat)

### DSL의 경우 무시해도 된다.
> Domain Specific Language

- 도메인 특화 언어라고 불린다.

### 결론

우리는 연산자를 오버로딩할 때는 그 이름의 의미에 맞게 사용을 해야 한다.
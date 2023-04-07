
## use를 사용하여 리소스를 닫아라.

close 메서드를 사용해서 명시적으로 닫아야 하는 리소스들이 존재한다.

- InputStream, OutputStream
- Connection
- Reader(FileReader, BufferedReader) 등등

> AutoCloseable을 상속받는 Closeable Interface를 Impl 하고 있다.

### Closeable

```java
public interface Closeable extends AutoCloseable {

    public void close() throws IOException;
}
```

- close 함수를 갖고 있는 모습이다.
- 해당 class를 impl 하는 class들은 최종적으로 리소스에 대한 참조가 사라질 때 GC에서 수거한다.
- 하지만 굉장히 느리고, 그때까지 리소스를 유지하는 비용이 매우 크다.
- 그래서 보편적으로 try-finally 블록을 사용해서 처리한다.

```kotlin
fun countCharactersInFile(path: String): Int {
    val reader = BufferedReader(FileReader(path))
    try {
        return reader.lineSequence().sumOf { it.length }
    } finally {
        // try { ... } 이 끝나고서 실행되는 scope
        reader.close()
    }
}
```

하지만 위 코드에는 문제가 있다.

- 우리는 close에서 어떤 오류가 발생할 수 있는지 위에서도 봤다.
- `throws IOException` 이 발생할 수도 있다.
- 그렇기에 finally scope 내부에서 오류가 발생하면 따로 처리를 해주지 않기 때문에 문제가 될 수도 있다.

> 솔직히 코틀린스럽지 않고 자바스러운 코드라서 마음에 들지도 않는다.

### kotlin scope function을 활용해서 개선해보자.

```kotlin
fun countCharactersInFileUse(path: String): Int {
    BufferedReader(FileReader(path)).use { reader ->
        return reader.lineSequence().sumOf { it.length }
    }
}
```

```kotlin
@InlineOnly
inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    var exception: Throwable? = null
    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when {
            apiVersionIsAtLeast(1, 1, 0) -> this.closeFinally(exception)
            this == null -> {}
            exception == null -> close()
            else ->
                try {
                    close()
                } catch (closeException: Throwable) {
                    // cause.addSuppressed(closeException) // ignored here
                }
        }
    }
}
```

- use 함수 내부에서 try catch finally를 구현한 모습이다.
- Closable은 함수가 실행되면서 문제가 있다면 catch에서 잡히고 exception에 현재 Throwable을 할당한다.
- finally에서는 kotlin 버전에 따라서 조금 다르지만 현재 버전을 기준으로 설명하자면 아래와 같다.
  - exception이 null 즉 catch로 잡힌 exception이 없다면 close를 실행한다.
  - 만약 exception이 이미 존재한다면 close를 try catch로 감싸서 처리한다.
  - 이런식으로 우리가 직접 try catch를 구현한다면 매우 귀찮은 구현이 됐을 테지만 use라는 scope function이 안전하고 편리하게 close 해주는 모습이다.

```kotlin
// 이런식으로 useLines도 존재한다.
// 내부적으로 BuffredReader를 사용하고 use scope function을 사용한다.
fun countCharactersInFileUseLines(path: String): Int = File(path).useLines { lines ->
    lines.sumOf { it.length }
}
```

### 정리
- use를 사용하면 Closable/AutoCloseable을 구현한 객체를 쉽고 안전하게 처리할 수 있다.
- 파일을 처리할 때는 한 줄씩 읽어 들이는 useLines를 사용하는 것이 좋다.


## Unit?을 리턴하지 말라

> Unit은 의미 잇는 값을 반환하지 않는 함수의 반환 유형으로 자주 사용된다.


### Unit?을 리턴하지 말아야하는 이유

```kotlin
fun keyIsCorrect(key: String): Boolean = // ...
if(!keyIsCorrect(key)) return

// 아래처럼 사용 가능
fun verifyKey(key: String): Unit? = // ...
verifyKey(key) ?: return
```

- 이런식으로도 사용이 가능하다.
- 하지만 Unit?으로 Boolean을 표시한다고 할 때 오해의 소지가 충분히 생길 수 있다.


## 변수타입이 명확하지 않은 경우 확실하게 지정하라.

### 코틀린은 타입 추론 시스템이 존재한다.

```kotlin
val num: Int = 10
val name: String = "Macrcin"

// 아래와 같다.

val num = 10
val name = "Macrcin"
```

위 처럼 코틀린은 유형이 명확할 때 타입을 추론해서 지정해준다.

하지만 아래와 같은 코드일 때 어떤 타입인지 예측해보자.

```kotlin
val data = getUserData()
```

- 아마 예측으로는 UserData라는 Class일 것 같다.
- 하지만 확실하게 하자면 해당 함수의 반환값을 확인하거나, 마우스를 올려서 확인해야한다.

이러한 과정들은 다른 사람과 작업할 때 가독성을 떨어트리는 요인이 된다.

그렇기에 이런 Clss들은 타입을 지정해주면 코드를 훨씬 쉽게 읽을 수 있게 해준다.

```kotlin
val data: UserData = getUserData()
```
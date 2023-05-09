
## 일반적인 프로퍼티 패턴은 프로퍼티 위임으로 만들어라

> 코틀린은 재사용과 관련해서 프로퍼티 위임이라는 기능을 제공한다.

- by lazy
- by viewModels
- 등등등

다양한 위임 패턴이 존재하며 코틀린은 프로퍼티 위임을 사용해서 간단하고 type-safe하게 구현할 수 있다.


### 프로퍼티 위임 활용 예시

```kotlin
var token: String by LoggingProperty("")
var attempts: Int by LoggingProperty(0)

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
```

이 코드를 디컴파일 하면 아래와 같다

<img width="776" alt="image" src="https://github.com/lee-ji-hoon/study-objects-with-kotlin/assets/53300830/0b81677a-7e26-4af0-ad1a-fc87e43ce7f0">

> token, attempts 디컴파일한 결과

- token과 attempts는 `LoggingProperty` 객체로 생성이 된다.
- 접근제어자는 기본이므로 public에 최상위 객체이므로 get, set함수가 static으로 열려있다.

<img width="707" alt="image" src="https://github.com/lee-ji-hoon/study-objects-with-kotlin/assets/53300830/f02f2db6-53a7-4a13-b557-3a28495b4fcd">

> LoggingProperty 디컴파일한 결과

- getValue setValue가 2개 정의된 모습을 볼 수 있다.

### 알아두면 좋은 프로퍼티 델리게이터

- lazy
- Delegates.observable
- Delegates.vetoable
- Delegates.notNull



## 결과 부족이 발생할 경우 null과 Failure를 사용하라.

> 서버로부터 데이터를 불러 올려고 하는데 인터넷이 문제가 있거나, 파싱이 안된다거나 등등의 경우 

### 예외는 정보를 전달하는 방법으로 사용되선 안된다.

> 예외는 예외적인 상황이 발생했을 때 사용을 하는 것이다.

- 예외가 전파되는 과정을 제대로 추적하지 못하고 있다.
- 코틀린은 unchecked예외라 개발자가 이 예외를 처리하지 않을 수도 있다.
- 예외적인 상황을 처리하기 위해 만들어졌기 때문에 명시적인 테스트 만큼 빠르게 동작하지 않는다.

### Result와 null을 활용하자.

```kotlin

// readObjectOrNull에서 nullable 하기 때문에 elvis 연산자로 대응
val age = userText.readObjectOrNull<Person>()?.age ?: -1

// person 객체가 sealed class이다.
val age = when(person) {
    is Success -> person.age
    is Failure -> -1
}
```

- 이런 처리 방식은 try-catch 블럭보다 효율적이다.
  - 사용하기 쉽고더 명확하다.
- 예외는 놓칠수도 있지만 sealed class나 nullable한 타입의 경우 컴파일 타임에서 체크가 된다.

> 개발자는 항상 자신이 요소를 안전하게 추출할 것이라고 생각하며, 개발자는 누구든지 reader가 될 수 있다는 점을 명시하자.
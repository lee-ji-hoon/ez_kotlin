## 사용자 정의 오류보다는 표준 오류를 사용하라.

> require, check, assert 함수를 사용하면, 대부분의 코틀린 오류를 처리할 수 있다.

### 하지만 예측하지 못한 상황도 존재한다.

- JSON 형식을 파싱하는 라이브러리를 구현한다고 가정
- 인력된 JSON 파일의 형식에 문제가 있다면 JSONParsingException을 발생시킨다.
    - 이런 Exception을 나타내는 적절한 오류가 없으므로 사용자 정의 Exception을 만들게 된다.

```kotlin
sealed class CustomException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause) {
    
    object JSONParseException: CustomException("JSON Parsing 도중 문제가 생겼습니다.") 
}
```

### 그래도 가능하면 표준 오류를 사용하자.

- 표준 라이브러리의 오류는 많은 개발자가 알고 있다.
    - 많은 개발자가 알고 있다는 것은 유지보수와 재사용이 용이해진다는 말과 비슷하다.

### 일반적으로 사용되는 예시

- `IllegalArgumentException`, `IllegalStateException` : require, check를 사용해서 throw할 수 있는 예시
- `IndexOutOfBoundsException` : 인덱스 파라미터의 값이 범위를 벗어났다는 것을 나타낸다.
    - 일반적으로 컬렉션 또는 배열과 함께 사용된다.
    - `ArrayList.get(Int)`를 사용할 때 만약 벗어난 범위라면 throw 한다.
- `ConcurrentModificationException` : 동시 수정을 금지했음에도 불구하고 시도한 경우 throw 된다.
- `UnsupportedOperationException` : 사용자가 사용하려고 했던 메서드가 현재 객체에서는 사용할 수 없다는 것을 의미
    - 기본적으로는 사용할 수 없는 메서드는 클래스에 없애는 것이 베스트이다.
- `NoSuchElementException` : 사용자가 사용하려고 햇던 요소가 존재하지 않음을 나타낸다.
    - 예시로 내부에 요소가 없는 Iterable에 대해 next를 호출 했을 때 throw 한다. 

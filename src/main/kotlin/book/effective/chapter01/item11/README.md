
## 가독성을 목표로 설계하라

> 프로그래밍은 쓰기보다 읽기가 중요하다.
> 항상 가독성을 고려해야 하며 코드 한 줄 혹은 클래스 하나를 건드는데 일주일 이상이 걸릴수도 있다.

```kotlin
fun main() {
    val person: Person? = Person()
    // 1번
    if (person != null && person.isAdult) {
        showPerson(person)
    } else {
        showError()        
    }
    
    // 2번
    person?.takeIf { it.isAdult }
        ?.let { showPerson(it) }
        ?: showError()
}
```

책에 있던 예제 코드를 조금 편하게 쓴 코드이다.

- 1번의 코드의 경우 위에서 아래로 쭉 읽힌다.
- 2번의 코드의 경우 아래로 내려가다가 다시 위로 올라가서 읽게됐다.
- 이처럼 짧은 코드임에도 코드를 이해하는데 시간 차이가 있었다.

### 수정이 생겼을 때

- 1번 코드의 경우 `if else` 에 내가 원하는 내용을 추가하면 된다.
- 2번 코드는함수를 추가로 해야 한다.
  - 예를 들어 `run` 이라는 scope function을 사용한다던지 등등
- 이렇게 수정을 할때도 1번 코드가 훨씬 더 간편한 모습이다.

### 정리

- 인지 부화를 덜 일으키는 코드로 가자.
  - 가독성은 뇌가 프로그램의 작동 방식을 이해하는 과정을 더 짧게 만드는 것이다. => 익숙한 코드가 빠르게 읽힌다.
- 극단적으로 생각하지 말자.
  - let이 안좋다는게 아니다. 단지 굳이 쓸 필요가 없는 곳에 scope function을 쓴다던지 stream형태로 구현하는 것을 하지 말자는 이야기이다.
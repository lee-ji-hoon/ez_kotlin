
## 프로퍼티는 동작이 아니라 상태를 나타내야 한다.

우선 프로퍼티를 사용한 예시부터 보자.

```kotlin
// 파생 프로퍼티라고 부른다.
var date: Data
    get() = Date(millis)
    set(value) {
        millis = value.time
    }
```

### 프로퍼티 대신 함수를 사용하면 좋은 경우

- 연산 비용이 높거나, O(1) 보다 큰 경우
  - 관습적으로 프로퍼티를 사용할 때는 연산 비용이 어느정도인지는 신경을 쓰지 않는다.
  - 그렇기에 연산비용이 높다면 함수로 만들어서 사용하는 사람이 비용을 예측할 수 있다.
- 비즈니스 로직이 필요한 경우
- 결정적이지 않은 경우
  - 연속으로 두 번 했는데 다른 값이 나올 수 있다면, 함수를 사용하는 것이 좋다.
- 변환의 경우
- 게터에서 프로퍼티의 상태 변경이 일어나야 하는 경우

> 정리해보자면 ㅍ로퍼티는 상태 집합을 나타내고, 함수는 행동을 나타내야 한다.
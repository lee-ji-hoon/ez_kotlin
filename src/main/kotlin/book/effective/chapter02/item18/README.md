
## 코딩 컨벤션을 지켜라

### 컨벤셔을 지켜야하는 이유

- 어떤 프로젝트를 접해도 쉽게 이해가 가능하다.
- 다른 외부 개발자도 프로젝트 코드를 쉽게 이해할 수 있다.
- 다른 개발자도 코드의 작동 방식을 쉽게 추측할 수 있다.
- 코드를 병합하고, 한 프로젝트의 코드 일부를 다른 코드로 이동하는 것이 쉽다.

### 많은 사람들이 실수하는 예시

```kotlin
// 이런식으로도 작성이 가능하다.
class Person(val name: String, val username: String)

// 하지만 많은 파라미터를 갖고 있는 경우 아래와 같이 한 줄씩 작성해야 한다.
class Person(
    val id: Int,
    val name: String,
    val surname: String
): Human(id, name)
```

하지만 이런식으로 구현을 하는 경우가 존재한다.

```kotlin
class Person(val id: Int = 0,
    val name: String,
    val surname: String
    ): Human(id, name)
```

- 모든 클래스의 arugment는 클래스 이름에 따라서 다른 크기의 들여쓰기를 갖는다.
  - 클래스 이름을 변경할 때 모든 기본 생성자 파라미터의 들여쓰기를 조정해야 한다.
- 클래스가 차지하는 공간이 너무 크다.

## 정리

- 팀마다 컨벤션이 다를 수 있다.
  - 하지만 프로젝트의 컨벤션은 꼭 지켜야 한다.
- 코딩 컨벤션을 확실하게 읽고, 정적 검사기(lint) 같은 도구를 사용해서 코딩 컨벤션의 일관성을 유지하자.

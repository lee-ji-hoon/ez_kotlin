
## 일반적인 알고리즘을 구현할 때 제네릭을 사용하라

> 제네릭을 사용한 함수를 제네릭 함수라고 부른다.

### 타입 소거

타입 마라미터를 사용하면 개발자는 여러 이득을 볼 수 있다.
- 하나의 함수로 여러 타입에서 사용할 수 있다.

하지만 JVM에서 바이트 코드의 제한으로 인해 컴파일 시점에서 제네릭과 관련된 정보는 사라진다.
- 이것을 방지하기 위해서 Kotlin에서는 타입을 유지시키는 방법이 존재한다.
- `refeid`이다.

#### 제네릭은 Item25에서 좀 더 자세히 다룰 예정
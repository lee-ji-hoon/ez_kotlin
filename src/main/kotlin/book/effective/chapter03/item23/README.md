
## 아이템 23 타입 파라미터의 섀도잉을 피하라

> 프로퍼티와 파라미터가 같은 이름을 가질 수 있는데 이런 경우를 섀도잉이라고 한다.

```kotlin
class Forest(val name: String) {
    
    fun addTree(name: String) = Unit
}
```

> name이라는 네이밍이 같은 모습

### 타입파라미터에서도 섀도잉 현상이 일어날 수 있다.

```kotlin
sealed interface Tree {

    val age: Int

    data class Birch(
        override val age: Int = 0
    ) : Tree

    data class Squrce(
        override val age: Int = 0
    ) : Tree
}

class Forest<T : Tree> {
    fun <T : Tree> addTree(tree: T) = Unit
}
```

#### 내가 원한건 아래와 같다.

    1. Forest클래스가 만들어질 때 어떤 Tree를 기준으로 만들지 정한다.
    2. addTree도 Tree를 받지만 class에서 선언된 Tree를 기준으로 한다.

그럼 실제로 구현을 해보자.

```kotlin
fun main() {
    val forest = Forest<Tree.Birch>()

    forest.addTree(Tree.Birch())
    // 내가 의도한대로면 아래 코드는 에러가 나야한다.
    forest.addTree(Tree.Squrce())
}
```

마지막 addTree가 오류가 나야 하는데 오류 없이 끝난다.     
그 이유는 `fun<T : Tree> addTree(tree:T)` 는 함수에서 선언된 객체타입을 사용하기 때문이다.

즉 아래와 같다.

```kotlin
val forest = Forest<Tree.Birch>() // T는 Birch 

forest.addTree(Tree.Birch())  // T는 Birch (Forest에서 T와 다르다.)
forest.addTree(Tree.Squrce()) // T는 Squrce (Forest에서 T와 다르고 위 addTree의 T와 다르다.)
```

이처럼 내가 의도한 것과 다른 결과를 도출해버린다.     

그렇기에 아래 처럼 구현을 해야 한다.

```kotlin
class Forest<T : Tree>() {
    fun addTree(tree: T) = Unit
}

fun main() {
    val forest = Forest<Tree.Birch>()

    forest.addTree(Tree.Birch())
    forest.addTree(Tree.Squrce())
}
```

<img width="827" alt="image" src="https://github.com/boostcampwm-2022/android04-BEEP/assets/53300830/235a849b-9874-4a6b-b8e3-ab9c03e643ab">

### 정리

- 프로퍼티와 파라미터 섀도잉은 충분히 자주 있을 수 있다.
- 하지만 파라미터 섀도잉은 피하자.
  - 이건 발생했을 때 발견하기가 너무 어렵다.




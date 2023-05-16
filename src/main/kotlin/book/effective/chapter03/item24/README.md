
## 제네릭 타입과 variance 한정자를 활용하라.

> Variance 한정자는 제네릭 타입의 서브 타이핑 관계를 선언할 때 사용되는 키워드

- in과 out, invariant이 존재한다.

```kotlin
class Cup<T>
class Cup<out T>
class Cup<in T>

open class Dog
class Puppy : Dog()
```

> 예제로 사용될 클래스

### 공변성(out)

> covariant(공변셩) 은 시스템의 속성중 하나로 하위 타입 관계가 유지되는 것을 의미한다.

```kotlin
fun main() {
    val a: Cup<Dog> = Cup<Puppy>()
    val b: Cup<Puppy> = Cup<Dog>() // 안된다.
    val anys: Cup<Any> = Cup<Int>()
    val nothings: Cup<Nothing> = Cup<Int>() // 안된다.
}
```

out의 경우 현재 A가 B의 서브 타입이라고 가정할 때, Cup<A>가 Cup<B>의 서브타입이라는 의미이다.     
그럼 위 코드로 대입해서 보자.

- `Puppy` 는 `Dog` 의 서브타입이다  -> `O`
- `Dog` 는 `Puppy` 의 서브타입이다  -> `X`
- `Int` 는 `Any` 의 서브타입이다    -> `O`
- `Int` 는 `Nothing`의 서브타입이다 -> `X`

> Nothing은 어느것도 상속받지 않은 클래스이다. `public class Nothing private constructor()`

- 이처럼 out은 하위 타입만 허용한다.
- `val a: Cup<Dog> = Cup<Puppy>()` 
  - 이럴때 허용하는 주체는 `Cup<Dog>`다
  - 실제 구현체가 `Cup<Puppy>()` 일 뿐 타입은 `Cup<Dog>`이기 때문이다.


### 반공변성(in)

> contravariant(반공변성) 은 시스템의 속성중 하나로 상위 타입 관계가 유지되는 것을 의미한다.

```kotlin
fun main() {
    val a: Cup<Dog> = Cup<Puppy>() // 안된다.
    val b: Cup<Puppy> = Cup<Dog>() 
    val anys: Cup<Any> = Cup<Int>() // 안된다.
    val nothings: Cup<Nothing> = Cup<Int>() 
}
```

- `Puppy` 는 `Dog` 의 상위타입이다  -> `X`
- `Dog` 는 `Puppy` 의 상위타입이다  -> `O`
- `Int` 는 `Any` 의 상위타입이다    -> `X`
- `Int` 는 `Nothing`의 상위타입이다 -> `O`

> Nothing을 out 에서 어느것도 상속받지 않은 클래스라고는 했지만 Kotlin에서 Nothing은 모든 타입의 하위타입이다.

- in은 상위 타입만 가능하다.
- 나머지 내용은 out과 동일하며 상위 타입인지, 하위 타입인지가 중요하다.

### 코틀린의 함수 타입

- 코틀린의 함수 타입의 모든 파라미터 타입은 contravariant(반공변성)이다.
- 모든 리턴 타입은 covariant(공변성)이다.

### variance 한정자의 안전성

자바의 배열은 covariant(공변성)이다.    
- 배열을 기반으로제네릭 연산자는 정렬 함수 등을 만들기 위해서 이다.
- 하지만 이것으로 인해 큰 문제가 발생한다.

```java
public class Test {
  public static void main(String[] args) {
    Integer[] numbers = {1, 4, 2, 1};
    Object[] objects = numbers;
    objects[2] = "B"; // ArrayStoreException 발생
  }
}
```

- numbers를 `Interger[]` 에서 `Object[]` 로 타입 캐스팅하는 것은 문제가 안된다.
  - 자바에서 배열은 코틀린에서 out을 사용중이라고 생각하면 된다.
  - `Integer` 는 `Object`의 하위 타입이기 때문에 할당이 가능한 것이다.
- 하지만 내부는 그대로 `Integer` 를 사용 중이다.
- 그렇기에 배열에 String 타입을 할당하면 오류가 발생한다.


    코틀린에서는 이것을 해결하기 위해서 아래와 같은 방법을 사용했다.
    - Array(IntArray, CharArray) 등을 invariant로 만들었다.
    - Array<Int>를 Array<Any>로 바꿀 수 없다. (in 이기 때문이다.)
      - Int가 Any의 상위 타입이 아니기 때문









## 이름 있는 아규먼트를 사용하라

우선 argument가 무엇인지 알기 위해서는 arugment가 안썼을때와 썼을때를 보자.

```kotlin
val text = (1..10).joinToString("|")
```

- `|` 는 아마 어떤 것을 구분하고자 하는 구분자 같다.
- 그러면 얘가 접두사로 들어갈지 접미사로 들어갈지 어떻게 알 수 있을까?

그런 당신을 위해 kotlin에서는 name argument가 존재한다.

```kotlin
val text = (1..10).joinToString(separator = "|")
```

이렇게 명시적으로 직접 지정하면서 명확하게 할 수 있다.

### 자바에서는 어떨까?

우선 자바는 name argument가 존재하지 않는다.    
그렇기에 다양한 문제들이 있는데 data class 혹은 java 코드에 copy 메서드가 존재한다고 가정하자.    

```kotlin
data class DataClass(
    val data1: String = "",
    val data2: String = "",
    val data3: String = "",
    val data4: String = "",
)
```

이런 data class가 있는데 java 파일에서 어떤 값을 copy하는데 특정 값 하나만 수정하고 싶다면 어떻게 될까?

```java
public class JavaClass {
    private static void test() {
        DataClass dataClass = new DataClass();
        dataClass.copy(
                dataClass.getData1(),
                dataClass.getData2(),
                dataClass.getData3(),
                // 아래 4번째 값만 하나 수정하고자 함
                "수정값이에요"
        );
    }
}
```

위처럼 값 하나만 수정하는데 1,2,3 번 파라미터 값도 넣어줘야 한다.    
만약 코틀린이였다면 `dataClass.copy(data4 = "수정값이에요")` 로 끝날일인데 말이다.    
이처럼 name argument는 개발자가 편하게 개발할 수 있게 도와준다.    


## 리시버를 명시적으로 참조하라

이번 아이템은 

```kotlin
class Node(
    private val name: String
) {
    fun makeChild(
        childName: String
    ) = create("$name.$childName").apply {
        println("Created $name")
    }

    private fun create(
        name: String
    ): Node? = Node(name)
}

fun main() {
    val node = Node("parent")
    node.makeChild("child")
}
```

> Node가 Nullable일 이유는 없지만 극단적인 예시로 보자.

### 출력 결과

출력 결과를 예측해보자. 아마도 `Created parent.child` 가 나올 것이라고 예측이 될 것이다.    
실제로 나도 그렇다고 예측을 했지만 결과는 아래와 같다.

<img width="252" alt="image" src="https://user-images.githubusercontent.com/53300830/234573983-3072e4f4-bab8-4977-9b4f-92bf8162795a.png">

### 이유가 뭘까? 한 번 java로 디컴파일도 해보자.

<img width="531" alt="image" src="https://user-images.githubusercontent.com/53300830/234574401-865c3bdd-651d-42d4-b64d-f869d6d193e0.png">

디컴파일 결과를 보면 되게 특이하다.

- Node의 create함수를 이용해서 `Node var2`를 만든다.
- var4 = false -> ~~~얘는 뭔지 모르겠다 왜 나온거지?~~~
- 이제 출력문이 만들어진다.
  - `"Created + this.name"`
  - 그럼 여기서 this.name은 뭘까?
  - this는 지금 Node를 뜻하므로 this.name은 child가 추가되기 전 parent 값이다.
  - 그 이유는 childName이 추가된 것은 새로운 `Node var2` 이기 때문이다.
  - 현재 Node는 아무런 영향을 받지 않았다.

그렇기에 이런 출력 결과 나온 것으로 보인다.       
책에서는 왜 이렇게 나왔는지 이유를 알려면 리시버를 명시해보라고 한다. 한 번 해보자.


```kotlin
println("Created ${this?.name}") // this가 nullable하므로 unpack후 호출해야 한다.
```

### 그러면 이번 디컴파일 결과는 어떨까?

<img width="576" alt="image" src="https://user-images.githubusercontent.com/53300830/234575599-42cc7048-2139-482c-a852-e7f501321d38.png">

> 하이라이팅된 부분만 봐보자!

`String var5 = "Created + (var != null ? var2.name : null");`

이전과는 다르게 var이 null 아니라면 var2의 name을 갖고 온다.    
즉 child 값이 추가된 상태로 갖고 온다는 것을 알 수 있다.     
그러면 출력 값도 한 번 보자!

<img width="683" alt="image" src="https://user-images.githubusercontent.com/53300830/234575942-092bac06-42e0-4a85-8fbd-2fd51fa62aad.png">

> 우리가 원했던 출력 값이 나오는 모습이다.     

### 정리

- 사실 apply를 쓰기에 적합한 상황은 아니였다.
- 단지 책에서 예시로 보여주기 위해서 사용한 모습이다. 보통이면 저기엔 also, let을 사용하게 될 것이다.
  - 둘의 공통점은 리시버를 무조건적으로 지정을 하게 되기 때문이다. `it.~`
- DSL 마커 이야기도 있지만 이 내용은 나중에 공부를 하게 되면 추가적으로 해보자 지금은 굳이 라는 생각이 든다.


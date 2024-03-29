
## 단위 테스트를 만들어라.

> 사용자의 관점에서 애플리케이션 외부적으로 제대로 작동하는지 확인하는 것이 주된 목표이다.


### 단위 테스트에서 확인해야 하는 사항들

- UseCase 요소가 사용될 거라고 예상되는 방법 테스트
- 일반적인 오류 케이스와 잠재적인 문제
- edge case와 잘못된 argument

이러한 단위테스트는 개발자가 만들고 있는 요소가 제대로 동작하는지를 빠르게 피드백 해주므로 개발하는 동안 큰 도움이 된다.

> TDD라는 접근 방식도 존재하는데, 이 방법은 개발 전에 테스트를 먼저 작성하고, 테스트를 통과시키는 것을 목적으로 하나하나 구현해 나간다.


### 단위 테스트의 장점

- 테스트가 잘 된 요소는 신뢰할 수 있다.
- 리팩터링의 사용되는 리소스가 적다.
    - 테스트가 있으므로 리팩터링 전 후를 비교했을 때 둘 다 통과한다면 신뢰가 가능하다.
    - 반면 테스트가 없다면 실수로 오류를 발생시킬 수도 있다보니 레거시 코드를 수정하려는 것을 두려워하게 된다.
- 수동으로 테스트하는 것보다 단위 테스트로 확인하는 것이 빠르다.
    - 실제로도 회사 앱을 테스트 할려면 적어도 1분 이상이 걸리는데 테스트 코드는 즉각 실행이 가능하다.

### 단위 테스트의 단점

- 만드는데 시간이 오래 걸린다.
- 활용할 수 있게 코드를 조정해야 한다.
  - ViewModel에서 Android 의존성을 없앤다던지 등등
- 좋은 단위 테스트를 만드는 것은 어렵다.
    - 잘못 만들어진 단위 테스트는 득보다 실이 크다.
    - 삡을 만들때도 잘못된 테스트 때문에 해당 테스트 코드를 전부 지워야 했던 경험이 있다.

### 정리

- 효과적인 단위 테스트를 하는 방법을 습득하고, 단위 테스트를 위한 코드를 작성하는 것은 매우 어렵다.
- 복잡하거나, 계속 수정이 일어나고 리팩터링이 일어날 수 있는 부분들에 한해서는 테스트를 하는 것을 권장한다.
- 비즈니스 로직 부분도 테스트를 하기 좋은 곳이다.

> 1장에서는 프로그램이 올바르게 작동해야 한다는 것을 최우선적인 목표로 두고 살펴보았다.    
> 가장 중요한 것은 애플리케이션이 진짜로 올바르게 동작하는 것이 중요하고 이것을 검증하는 것이 테스트이다.    
> 이런 테스트를 개발 과정에서 가장 효율적으로 활용할 수 있는 것은 단위 테스트이다.
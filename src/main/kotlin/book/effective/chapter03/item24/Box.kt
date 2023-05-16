package book.effective.chapter03.item24

class Box<out T> {

    private var value: T? = null

    // 파라미터 타입은 기본적으로 contravariant 즉 in(하위 타입만 올 수 있다) 이다.
    fun set(value: @UnsafeVariance T) {
        this.value = value
    }
}

class Box2<in T> {

    // 여기는 public out 위치이기 때문에 in(하위 타입)이 들어오면 안된다.
    var value: @UnsafeVariance T? = null
}

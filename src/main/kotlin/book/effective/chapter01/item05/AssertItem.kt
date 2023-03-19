package book.effective.chapter01.item05

fun main() {
    AssertItem().popAssert(10)
    AssertItem().popCheck(10)
}

class AssertItem {

    fun popAssert(num: Int = 1): Int {
        // 생략
        assert(num == 0) // 표준 애플리케이션 실행시에 assert가 예외를 throw 하지 않는다.
        return num
    }

    fun popCheck(num: Int = 1): Int {
        check(num == 0) // check는 IllegalStateException throw 한다.
        return num
    }
}
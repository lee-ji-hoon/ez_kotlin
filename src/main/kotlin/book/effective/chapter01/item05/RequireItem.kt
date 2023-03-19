package book.effective.chapter01.item05

import java.awt.Point

class RequireItem {

    private fun factorial(n: Int): Long {
        require(n >= 0) // 조건을 만족하지 못하면 IllegalArgumentException throw 한다.
        // 이렇게 조건을 만족하지 못하면 밑에 작업을 못하게 하기 위해서 맨 위에 선언을 한다.
        require(n >= 0) { "0 미만인 수가 입력이 됐습니다."} // throw 메시지 지정 가능
        return if (n <= 1) 1 else factorial(n - 1) * n
    }

    private fun findClusters(points: List<Point>): List<Point> {
        require(points.isNotEmpty())
        return listOf()
    }

    private fun sendEmail(userName: String?, userEmail: String) {
        requireNotNull(userName)
        require(isValidEmail(userEmail))
    }

    private fun isValidEmail(userEmail: String) = userEmail.length > 10
}
package example.etc


sealed interface History {
    val date: Int
    val gifticonId: String

    data class Init(
        override val date: Int,
        override val gifticonId: String,
    ) : History

    data class Use(
        override val date: Int,
        override val gifticonId: String,
        val location: String?,
    ) : History

    data class UseCashCard(
        override val date: Int,
        override val gifticonId: String,
        val amount: Int,
        val location: String?,
    ) : History
}

sealed class History2(
    open val date: Int,
    open val gifticonId: String
) {

    data class Init(
        override val gifticonId: String,
    ) : History2(10, gifticonId)

    data class Use(
        override val date: Int,
        override val gifticonId: String,
        val location: String?,
    ) : History2(date, gifticonId)

    data class UseCashCard(
        val amount: Int,
        val location: String?,
    ) : History2(10, "이런식으로 자식 클래스들에게 강제가 불가능")
}


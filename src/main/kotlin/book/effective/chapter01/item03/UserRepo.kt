package book.effective.chapter01.item03

interface UserRepo {

    // Declaration has type inferred from a platform call, which can lead to unchecked nullability issues.
    // Specify type explicitly as nullable or non-nullable
    fun getUserName() = NullableNotNull().value
}

class RepoImpl : UserRepo {

    override fun getUserName(): String? {
        return null
    }
}

fun main() {
    val repo: UserRepo = RepoImpl()
    val text: String = repo.getUserName() // 런타임에서 NPE 발생
    println("User name length is ${text.length}")
}
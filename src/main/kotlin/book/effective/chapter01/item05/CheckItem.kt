package book.effective.chapter01.item05

class CheckItem {
    private val token: String? = null
    fun speak(text: String) {
        check(isInitialized) // 만족하지 못할시 IllegalAccessException 을 throw 한다.
        // ... 이후 로직
    }

    fun getUserInfo(): String {
        checkNotNull(token)
        // ... 이후 로직
        return "유저 정보"
    }

    companion object {
        private const val isInitialized = true
    }
}
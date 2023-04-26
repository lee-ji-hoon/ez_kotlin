package item10

import example.etc.ifNullOrBlank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ExtensionsTest {

    data class Person(
        val name: String
    )

    @Test
    @DisplayName("CharSequence 가 null일 때")
    fun isNUll() {
        val value = isNull.ifNullOrBlank {
            assertTrue { isNull == null }
            "isNullOrBlankResult"
        }
        assertTrue { value == "isNullOrBlankResult" }
    }

    @Test
    @DisplayName("CharSequence 가 empty일 때")
    fun isEmpty() {
        val value = isEmpty.ifNullOrBlank {
            assertTrue { isEmpty.isEmpty() }
            "isNullOrBlankResult"
        }
        assertTrue { value == "isNullOrBlankResult" }
    }

    @Test
    @DisplayName("CharSequence 가 null도 아니고 empty도 아닐 때")
    fun isChar() {
        val value = char.ifNullOrBlank {
            assertTrue { char.isNullOrBlank().not() }
            "isNullOrBlankResult"
        }
        // char은 empty 나 null이 아니므로 초기값을 갖고 있어야 한다.
        assertTrue { value == "char" }
    }

    @Test
    @DisplayName("CharSequence 가 null 인데 다른 타입으로 캐스팅 될 때")
    fun isCharToPerson() {
        val value = isNull?.ifNullOrBlank {
            assertTrue { char.isNullOrBlank().not() }
            Person("이지훈")
        }
        assertTrue { value is Person }
    }

    companion object {
        val isNull: CharSequence? = null
        val isEmpty: CharSequence = ""
        val char: CharSequence? = "char"
    }
}




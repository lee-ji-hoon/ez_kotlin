package book.effective.chapter01.item05

import kotlin.math.log

class SmartCasting {

    sealed class Car {
        object Hyundai : Car() {
            fun infoHyundai() {
                println("난 현대 차야")
            }
        }

        object Kia : Car() {
            fun infoKia() {
                println("난 기아 차야")
            }
        }
    }

    fun changeCar(car: Car) {
        // require과 check 블록으로 어떤 조건을 확인해서 true가 나왔다면 해당 값은 이후에도 true일 것이라고 가정을 한다.
        require(car is Car.Kia)
        println(car.infoKia()) // 위에서 kia인지 확인했기에 kia만의 함수를 사용할 수 있다. -> 스마트 캐스팅
    }

    fun sendEmail(name: String?) {
        // return과 throw를 Elvis와 함께 사용한다면 nullable 확인할 때 매우 유용하다.
        val email = name ?: run {
            println("이름이 없어요~")
            return
        }
    }
}
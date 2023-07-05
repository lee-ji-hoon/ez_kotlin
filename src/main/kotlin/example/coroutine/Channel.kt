package example.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

sealed class Menu {
    data class Cappuccino(
        val name: String,
        val type: String,
    ) : Menu()
    // ...
}

fun main() = runBlocking {
    // 주문한 커피 목록
    val orders = listOf(
        Menu.Cappuccino("Regular", "Whole"),
        Menu.Cappuccino("Decaf", "Whole"),
        Menu.Cappuccino("Regular", "NonFat"),
        Menu.Cappuccino("Premium", "Whole")
    )

    printlnWithCoroutine(orders.toString())
    // Channel을 통해 주문들을 보낸다.
    val ordersChannel = Channel<Menu>()
    launch {
        for (order in orders) {
            ordersChannel.send(order)
        }
        // 만약 Channel을 종료하지 않는다면 어떻게 되는지 주석처리 해봐도 좋다.
        ordersChannel.close()
    }

    val takenTime = measureTimeMillis {
        // 바리스타 1, 2는 ordersChannel을 통해 내려온 주문들을 받아 커피를 만든다.
        launch(CoroutineName("barista-1")) { makeCoffee(ordersChannel) }
        launch(CoroutineName("barista-2")) { makeCoffee(ordersChannel) }
    }
    printlnWithCoroutine("Execution time: $takenTime ms")
}

private suspend fun makeCoffee(ordersChannel: ReceiveChannel<Menu>) {
    for (order in ordersChannel) {
        printlnWithCoroutine("Processing order: $order")
        delay(2.seconds)
        printlnWithCoroutine("Served")
    }
}

// VM option에 추가 후 실행 -Dkotlinx.coroutines.debug
fun printlnWithCoroutine(text: String) {
    println("[${Thread.currentThread()}] $text")
}
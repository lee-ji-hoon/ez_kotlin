package example.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    test2(this)
}

suspend fun test2(coroutineScope: CoroutineScope) {
    val job = coroutineScope.launch {
        runSuspendCatching {
            longRunningTask2()
            println("coroutine is finished")
        }
    }

    coroutineScope.launch {
        delay(5000L)
        println("Cancelling")
        job.cancel()
    }
}

suspend fun longRunningTask2() {
    repeat(10) { i ->
        println("Executing network call $i")
        networkCall2()
    }
}

suspend fun networkCall2() = delay(1000L)
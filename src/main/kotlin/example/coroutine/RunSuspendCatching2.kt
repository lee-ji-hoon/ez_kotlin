package example.coroutine

import kotlinx.coroutines.*
import java.lang.Exception

fun main(): Unit = runBlocking() {
    test(this)
}

fun test(coroutineScope: CoroutineScope) {
    val job = coroutineScope.launch {
        longRunningTask()
        println("coroutine is finished")
    }


    coroutineScope.launch {
        delay(1000L)
        println("나 죽어염")
        throw IllegalStateException()
    }

    coroutineScope.launch {
        delay(1000L)
        println("Cancelling")
        job.cancel()
    }
}

suspend fun longRunningTask() {
    repeat(10) { i ->
        delay(1L)
        runCatching {
            delay(1000L) // 얘가 실행하면서 Exception 있는지 던진다.
            println("Executing network call $i ${Thread.currentThread()}")
        }
    }
}

suspend fun networkCall() = delay(1000L)

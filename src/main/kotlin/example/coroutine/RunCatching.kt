package example.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException


fun main(): Unit = runBlocking() {
    kotlin.runCatching {
        launch {
            cancel()
            delay(100L)
        }
    }
        .onSuccess {
            println("success")
        }
        .onFailure {
            println("throw -> $it")
        }
}


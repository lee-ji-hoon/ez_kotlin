package example.coroutine

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    supervisorScope {
        launch {
            runSuspendCatching {
                println("업로드 시작")
                delay(2000L)
                println("업로드 종료")
            }
                .onSuccess {
                    println("업로드 성공")
                }
                .onFailure {
                    println("업로드 실패")
                }
        }

        val job = launch {
            launch {
                runSuspendCatching {
                    println("B작업 시작")
                    delay(1000L)
                    println("B작업 종료")
                }
                    .onSuccess {
                        println("B작업 성공")
                    }
                    .onFailure {
                        println("B작업 실패 $it")
                    }
            }
        }

        delay(500L)
        job.cancel()
    }
}


internal inline fun <T, R> T.runSuspendCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        println("CancellationException")
        throw c
    } catch (e: Throwable) {
        println("Throwable $e")
        Result.failure(e)
    }
}
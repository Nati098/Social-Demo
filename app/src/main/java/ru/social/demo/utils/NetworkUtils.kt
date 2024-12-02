package ru.social.demo.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NetworkUtils {

    suspend fun <T> makeCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        call: suspend () -> T
    ): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }

}
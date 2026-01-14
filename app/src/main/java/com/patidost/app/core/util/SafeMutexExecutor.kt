package com.patidost.app.core.util

import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.sync.Mutex
import timber.log.Timber

suspend fun <T> executeWithLock(
    mutex: Mutex,
    block: suspend () -> T
): Resource<T> {
    if (mutex.tryLock()) {
        return try {
            val result = block()
            Resource.Success(result)
        } catch (e: Exception) {
            Timber.e(e, "Atomic operation failed with an exception.")
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred."))
        } finally {
            mutex.unlock()
            Timber.d("Mutex unlocked.")
        }
    } else {
        Timber.d("Concurrent operation rejected: Mutex is locked.")
        return Resource.Error(UiText.DynamicString("Operation already in progress."))
    }
}

package com.patidost.app.core.util

import kotlinx.coroutines.sync.Mutex
import timber.log.Timber

/**
 * Patidost Technical Constitution - Article 24: Atomic Operation Guarantee
 *
 * A centralized, reusable function to ensure that a critical operation (block) is executed
 * only once at a time, preventing race conditions and double-submits.
 *
 * This function uses a Mutex to acquire a lock before executing the block. If the lock is
 * already held, the new invocation is rejected, and a warning is logged.
 *
 * @param T The return type of the operation block.
 * @param mutex The Mutex instance to be used for locking. It should be a single instance
 *              scoped to the lifecycle of the caller (e.g., a ViewModel).
 * @param block The suspend block of code to be executed atomically.
 * @return A [Resource] wrapping the result of the operation: [Resource.Success] if the block
 *         executes successfully, or [Resource.Error] if an exception occurs or the operation
 *         is rejected because a previous one is still in progress.
 */
suspend fun <T> executeWithLock(
    mutex: Mutex,
    block: suspend () -> T
): Resource<T> {
    // Try to acquire the lock. If it's already locked, another operation is in progress.
    if (mutex.tryLock()) {
        return try {
            // Execute the operation block.
            val result = block()
            Resource.Success(result)
        } catch (e: Exception) {
            // Log the exception for forensic analysis.
            Timber.e(e, "Atomic operation failed with an exception.")
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred."))
        } finally {
            // Legal Eviction: The lock MUST be released in all cases.
            mutex.unlock()
            Timber.d("Mutex unlocked.")
        }
    } else {
        // Architectural Veto: A second concurrent operation was rejected.
        Timber.d("Concurrent operation rejected: Mutex is locked.")
        // Optionally, return a specific error to the UI to give feedback.
        return Resource.Error(UiText.DynamicString("Operation already in progress."))
    }
}

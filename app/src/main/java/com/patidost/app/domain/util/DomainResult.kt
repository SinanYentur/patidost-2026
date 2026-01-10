package com.patidost.app.domain.util

/**
 * 🛡️ DomainResult - Sovereign Standard V10000.70000.
 * Rule 100: Standardized result wrapper for Global Launch.
 * V2: Removed internal AppError declaration to resolve Redeclaration conflict.
 */
sealed class DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    data class Error(val error: AppError) : DomainResult<Nothing>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    inline fun onSuccess(action: (T) -> Unit): DomainResult<T> {
        if (this is Success) action(data)
        return this
    }

    inline fun onFailure(action: (AppError) -> Unit): DomainResult<T> {
        if (this is Error) action(error)
        return this
    }
    
    fun getOrNull(): T? = (this as? Success)?.data
    
    fun exceptionOrNull(): Throwable? = (this as? Error)?.error?.let { 
        Exception(it.message)
    }
}

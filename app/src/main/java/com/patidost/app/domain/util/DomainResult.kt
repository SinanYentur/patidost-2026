package com.patidost.app.domain.util

import com.patidost.app.domain.util.AppError

/**
 * 🛡️ Rule 100: Pure Domain Result.
 * Fixed Syntax: Package declaration separated from class definition.
 */
sealed class DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    data class Error(val error: AppError) : DomainResult<Nothing>()
}

sealed class AppError {
    sealed class Network : AppError() {
        object NoConnection : Network()
        object Timeout : Network()
        data class ServerSide(val code: Int, val message: String?) : Network()
        object RateLimitExceeded : Network()
    }
    
    sealed class Auth : AppError() {
        object InvalidCredentials : Auth()
        object SessionExpired : Auth()
        object Unauthorized : Auth()
    }
    
    object Persistence : AppError() {
        object DiskFull : Persistence()
        object Corruption : Persistence()
    }
    
    data class Unknown(val throwable: Throwable? = null) : AppError()
}

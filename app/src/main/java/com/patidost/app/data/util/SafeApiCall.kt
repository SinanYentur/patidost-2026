package com.patidost.app.data.util

import com.patidost.app.core.util.Resource
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * A sealed interface to represent the result of a network call.
 */
sealed interface NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error<T>(val code: Int, val message: String?) : NetworkResult<T>
    data class Exception<T>(val e: Throwable) : NetworkResult<T>
}

/**
 * A utility function to make a safe API call and handle exceptions.
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            NetworkResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    NetworkResult.Error(throwable.code(), throwable.response()?.errorBody()?.string())
                }
                else -> {
                    NetworkResult.Exception(throwable)
                }
            }
        }
    }
}

package com.patidost.app.data.remote.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * A sealed class to wrap the result of a network call, representing either success or a classified failure.
 * This is a core component of the "Safe Network Backbone".
 */
sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: NetworkException) : NetworkResult<Nothing>()
}

/**
 * Executes a network API call safely and returns a [NetworkResult].
 * This function is the single entry point for all repository-level network requests.
 * It enforces the "Network Error Law" and "Network Failure Doctrine" by catching all throwables
 * and mapping them to a controlled set of [NetworkException]s.
 *
 * @param dispatcher The CoroutineDispatcher to execute the call on. Defaults to [Dispatchers.IO].
 * @param apiCall The suspend lambda function representing the Retrofit API call.
 * @return A [NetworkResult] wrapping the success data or a classified [NetworkException].
 */
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            NetworkResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is SocketTimeoutException -> NetworkResult.Error(NetworkException.Timeout("Request timed out"))
                is IOException -> NetworkResult.Error(NetworkException.Unavailable("Network unavailable. Please check your connection."))
                is HttpException -> {
                    val code = throwable.code()
                    val message = throwable.message() // Placeholder for a proper error body parser
                    when (code) {
                        401 -> NetworkResult.Error(NetworkException.Unauthorized())
                        403 -> NetworkResult.Error(NetworkException.Forbidden())
                        in 400..499 -> NetworkResult.Error(NetworkException.ClientError(code, message))
                        in 500..599 -> NetworkResult.Error(NetworkException.ServerError(code, message))
                        else -> NetworkResult.Error(NetworkException.Unknown("Unknown HTTP error: $code"))
                    }
                }
                is SerializationException -> NetworkResult.Error(NetworkException.Serialization(throwable.message ?: "Error decoding response."))
                else -> NetworkResult.Error(NetworkException.Unknown(throwable.message ?: "An unknown error occurred."))
            }
        }
    }
}

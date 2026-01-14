package com.patidost.app.data.util

import com.patidost.app.core.util.Resource
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Resource.Error(UiText.DynamicString("Network error. Please check your connection."))
                is HttpException -> {
                    val message = when (throwable.code()) {
                        401 -> "Unauthorized. Please login again."
                        403 -> "You don\'t have permission to access this."
                        404 -> "Content not found."
                        429 -> "Too many requests. Please try again later."
                        in 500..599 -> "A server error occurred."
                        else -> "An unknown error occurred."
                    }
                    Resource.Error(UiText.DynamicString(message))
                }
                else -> {
                    Resource.Error(UiText.DynamicString("An unknown error occurred."))
                }
            }
        }
    }
}

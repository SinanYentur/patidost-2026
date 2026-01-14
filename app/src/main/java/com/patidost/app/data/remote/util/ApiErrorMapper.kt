package com.patidost.app.data.remote.util

import retrofit2.HttpException

/**
 * A placeholder for a future error body parser.
 * The existence of this file enforces the rule that raw error bodies should not be used directly.
 * It will be responsible for converting HTTP error bodies into meaningful error objects.
 */
object ApiErrorMapper {

    /**
     * Parses an [HttpException] to extract a structured error message from its body.
     *
     * @param exception The [HttpException] to parse.
     * @return A more meaningful error message string.
     */
    fun parseHttpError(exception: HttpException): String {
        // Future implementation: Use a JSON serializer (like kotlinx.serialization)
        // to parse the errorBody() of the exception into a specific error DTO.
        // For now, we return a generic message.
        return "HTTP Error ${exception.code()}: ${exception.message()}"
    }
}

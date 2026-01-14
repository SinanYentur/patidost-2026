package com.patidost.app.data.remote.util

import java.io.IOException

/**
 * A set of sealed classes representing classified network errors.
 * This enforces the "Network Error Law" by preventing raw exceptions from leaking to upper layers.
 */
sealed class NetworkException(message: String) : IOException(message) {

    /**
     * Represents a server-side error (HTTP 5xx).
     */
    data class ServerError(val code: Int, override val message: String) : NetworkException("Server error: $code - $message")

    /**
     * Represents a client-side error (HTTP 4xx), such as a bad request or not found.
     */
    data class ClientError(val code: Int, override val message: String) : NetworkException("Client error: $code - $message")

    /**
     * Represents an authentication error (HTTP 401).
     */
    data class Unauthorized(override val message: String = "Unauthorized") : NetworkException(message)

    /**
     * Represents a permission error (HTTP 403).
     */
    data class Forbidden(override val message: String = "Forbidden") : NetworkException(message)

    /**
     * Represents a network connectivity issue (no internet, DNS problem).
     */
    data class Unavailable(override val message: String = "Network unavailable") : NetworkException(message)

    /**
     * Represents a request timeout.
     */
    data class Timeout(override val message: String = "Request timed out") : NetworkException(message)

    /**
     * Represents an error during JSON serialization/deserialization.
     */
    data class Serialization(override val message: String) : NetworkException("Serialization error: $message")

    /**
     * Represents an unknown or unhandled network error.
     */
    data class Unknown(override val message: String = "An unknown network error occurred") : NetworkException(message)
}

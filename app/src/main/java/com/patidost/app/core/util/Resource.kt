package com.patidost.app.core.util

/**
 * A generic wrapper class that contains data and its state.
 * This will be the standard language for data flow from the data layer to the UI.
 * @param T The type of the data.
 * @param message An optional error message.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * Represents a successful state with data.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error state with an optional message.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Represents a loading state.
     */
    class Loading<T> : Resource<T>()
}

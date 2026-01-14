package com.patidost.app.core.util

import com.patidost.app.presentation.ui.util.UiText

/**
 * A constitutional state envelope for handling data layer operations.
 * It explicitly defines the states of a data request: Success, Error, and Loading.
 * @param T The type of the data.
 * @param message An optional UIText message, typically for errors.
 */
sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: UiText?, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

package com.patidost.app.presentation.ui.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * A sealed interface to represent text that can be either a dynamic string
 * or a string resource. This is used to avoid passing context to ViewModels.
 */
sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = resId, *args)
        }
    }
}

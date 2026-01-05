package com.patidost.app.presentation.auth.model

import com.patidost.app.domain.util.AuthError

/**
 * 🛡️ HubX 10/10: Presentation-Layer Result.
 * Carries the UI model or a Pure Domain Error.
 */
sealed class AuthUiResult {
    data class Success(val uiModel: AuthUiModel) : AuthUiResult()
    data class Error(val errorType: AuthError) : AuthUiResult()
}

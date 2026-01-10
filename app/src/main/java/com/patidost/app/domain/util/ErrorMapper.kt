package com.patidost.app.domain.util

import com.google.firebase.auth.FirebaseAuthException

/**
 * 🛡️ ErrorMapper - Sovereign Error Translation Matrix V1.
 * Translates generic exceptions into specific, domain-aware AppError types.
 */
fun Exception.toAppError(): AppError {
    return when (this) {
        is FirebaseAuthException -> AppError.FirebaseAuthError(this.message ?: "An unknown Firebase Auth error occurred.")
        // Can be expanded to handle other specific exception types
        else -> AppError.UnknownError(this.message ?: "An unknown error occurred.")
    }
}

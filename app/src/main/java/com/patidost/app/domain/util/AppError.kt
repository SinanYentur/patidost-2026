package com.patidost.app.domain.util

/**
 * 🛡️ AppError - Sovereign Error Standard V3.
 * Defines the hierarchy of possible business logic errors.
 * V3: Added NotAuthorized for session/token failures.
 */
sealed class AppError(val message: String) {
    class NetworkError(message: String) : AppError(message)
    class UnknownError(message: String) : AppError(message)
    class FirebaseAuthError(message: String) : AppError(message)
    class NotAuthorized(message: String = "Not authorized for this action") : AppError(message)
    object AccessDenied : AppError("Access Denied")
    // Can be extended with more specific error types
}

package com.patidost.app.domain.model.valueobject

/**
 * 🛡️ Rule 300: Domain Value Objects (Primitive Obsession Kill).
 * Ensures business rules are enforced at the type level.
 */
@JvmInline
value class EmailVO(val value: String) {
    init {
        require(value.contains("@") && value.contains(".")) { "Invalid Email Format" }
    }
}

@JvmInline
value class PasswordVO(val value: String) {
    init {
        require(value.length >= 6) { "Password must be at least 6 characters" }
    }
}

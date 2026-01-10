package com.patidost.app.domain.model

/**
 * 🛡️ User Domain Model - V10000.70018 Sovereign Standard.
 * Rule 300: Standardized with Empty Object Pattern and Trust Metrics.
 */
data class User(
    val id: String,
    val email: String,
    val name: String,
    val profileImageUrl: String = "",
    val isPremium: Boolean = false,
    val trustScore: Int = 0,
    val successfulAdoptions: Int = 0,
    val reportsCount: Int = 0
) {
    companion object {
        val EMPTY = User(id = "", email = "", name = "Misafir")
        
        fun create(id: String, email: String, name: String): User {
            require(email.contains("@")) { "Geçersiz e-posta adresi" }
            return User(id = id, email = email, name = name)
        }
    }
}

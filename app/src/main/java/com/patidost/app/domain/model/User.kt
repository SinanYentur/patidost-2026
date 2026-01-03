package com.patidost.app.domain.model

import kotlinx.serialization.Serializable

/**
 * User Domain Model - 2026 PRODUCTION Standard.
 * RVWL: Corrected package name and profile metadata.
 */
@Serializable
data class User(
    val id: String,
    val email: String,
    val displayName: String,
    val photoUrl: String,
    val phoneNumber: String,
    val bio: String,
    val isVerified: Boolean = false,
    val createdAt: Long
) {
    companion object {
        val EMPTY = User(
            id = "",
            email = "",
            displayName = "",
            photoUrl = "",
            phoneNumber = "",
            bio = "",
            isVerified = false,
            createdAt = 0L
        )
    }
}

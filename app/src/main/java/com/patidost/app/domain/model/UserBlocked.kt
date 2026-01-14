package com.patidost.app.domain.model

import java.util.Date

/**
 * Represents the relationship of a user blocking another user.
 * Pure domain model.
 */
data class UserBlocked(
    val id: String,
    val userId: String, // The user who is blocking
    val blockedUserId: String, // The user who is being blocked
    val createdAt: Date
)

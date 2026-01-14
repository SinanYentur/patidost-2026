package com.patidost.app.domain.model

import java.util.Date

/**
 * Represents a user-submitted report against another entity (user, pet, post, etc.).
 * Pure domain model.
 */
data class Report(
    val id: String,
    val targetType: String, // e.g., "user", "pet", "post"
    val targetId: String,
    val reporterId: String,
    val reason: String,
    val createdAt: Date
)

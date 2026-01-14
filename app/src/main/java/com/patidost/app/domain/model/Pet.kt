package com.patidost.app.domain.model

import java.util.Date

/**
 * Represents the core Pet entity in the domain layer.
 */
data class Pet(
    val id: String,
    val ownerId: String,
    val name: String,
    val age: Int,
    val breed: String,
    val gender: String,
    val description: String,
    val isVisibleInSwipe: Boolean,
    val state: String, // e.g., "active", "passive", "deleting"
    val createdAt: Date
)

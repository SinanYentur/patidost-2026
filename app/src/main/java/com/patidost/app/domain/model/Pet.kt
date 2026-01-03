package com.patidost.app.domain.model

import kotlinx.serialization.Serializable

/**
 * Pet Domain Model - 2026 PRODUCTION Standard.
 * RVWL: Corrected package name and synchronized with Phase 2 Action.
 */
@Serializable
data class Pet(
    val id: String,
    val name: String,
    val species: String,
    val breed: String,
    val age: Int,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val ownerId: String,
    val createdAt: Long
) {
    companion object {
        val EMPTY = Pet(
            id = "",
            name = "",
            species = "",
            breed = "",
            age = 0,
            imageUrl = "",
            price = 0.0,
            description = "",
            ownerId = "",
            createdAt = 0L
        )
    }
}

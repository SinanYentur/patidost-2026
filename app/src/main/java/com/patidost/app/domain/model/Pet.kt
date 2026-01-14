package com.patidost.app.domain.model

/**
 * Represents the core Pet entity in the domain layer.
 */
data class Pet(
    val id: String,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val age: Int,
    val patiPoints: Int,
    val owner: PetOwner
)

package com.patidost.app.domain.repository

/**
 * Represents the filtering criteria for a pet search.
 * @param category The type of pet (e.g., "Kedi", "Köpek").
 * @param minAge The minimum age for the pet.
 * @param maxAge The maximum age for the pet.
 * @param city The city where the pet is located.
 */
data class PetFilter(
    val category: String? = null,
    val minAge: Int? = null,
    val maxAge: Int? = null,
    val city: String? = null
)

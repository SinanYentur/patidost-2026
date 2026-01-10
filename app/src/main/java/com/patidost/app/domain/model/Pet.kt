package com.patidost.app.domain.model

/**
 * 🛡️ Pet Domain Model - V49.95.
 * RVWL: Unified data structure for all layers.
 */
data class Pet(
    val id: String,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val description: String,
    val species: String = "",
    val age: Int = 0,
    val price: Double = 0.0,
    val ownerId: String? = null,
    val isAdopted: Boolean = false
)

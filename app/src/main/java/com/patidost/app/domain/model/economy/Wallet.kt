package com.patidost.app.domain.model.economy

import java.time.Instant

/**
 * Represents a user's wallet, holding their Pati Point balance.
 * This is a core entity of the economy domain.
 */
data class Wallet(
    val userId: String,
    val patiBalance: Long,
    val lastUpdatedAt: Instant
)

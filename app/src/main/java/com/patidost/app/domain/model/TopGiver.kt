package com.patidost.app.domain.model

/**
 * Represents a user in the 'Top Givers' leaderboard.
 * This is a pure data class for the domain layer.
 */
data class TopGiver(
    val userId: String,
    val name: String,
    val avatarUrl: String?,
    val patiPointsGiven: Int
)

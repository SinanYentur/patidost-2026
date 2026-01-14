package com.patidost.app.domain.model

/**
 * Represents a user in the 'Top Givers' leaderboard.
 */
data class TopGiver(
    val userId: String,
    val name: String,
    val avatarUrl: String?,
    val patiPointsGiven: Int
)

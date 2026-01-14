package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName

/**
 * Represents the structure of a user in the leaderboard as stored in Firestore.
 * Uses @PropertyName for robust mapping from Firestore document fields.
 */
data class TopGiverUserDto(
    @get:PropertyName("user_id") @set:PropertyName("user_id") var userId: String = "",
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("avatar_url") @set:PropertyName("avatar_url") var avatarUrl: String? = null,
    @get:PropertyName("weekly_pati_given") @set:PropertyName("weekly_pati_given") var weeklyPatiGiven: Int = 0
)

/**
 * Represents the top-level document for a weekly leaderboard.
 */
data class TopGiverDto(
    @get:PropertyName("users") @set:PropertyName("users") var users: List<TopGiverUserDto> = emptyList()
)

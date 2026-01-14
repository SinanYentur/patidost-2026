package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

/**
 * Data Transfer Object for UserBlocked.
 * Matches the Firestore document structure exactly.
 */
data class UserBlockedDto(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("userId") @set:PropertyName("userId") var userId: String = "",
    @get:PropertyName("blockedUserId") @set:PropertyName("blockedUserId") var blockedUserId: String = "",
    @get:PropertyName("createdAt") @set:PropertyName("createdAt") @ServerTimestamp var createdAt: Date? = null
)

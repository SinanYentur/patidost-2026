package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName

/**
 * Data Transfer Object for UserProfile.
 * Matches the Firestore document structure exactly.
 */
data class UserProfileDto(
    @get:PropertyName("userId") @set:PropertyName("userId") var userId: String = "",
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("bio") @set:PropertyName("bio") var bio: String = "",
    @get:PropertyName("photoUrl") @set:PropertyName("photoUrl") var photoUrl: String = ""
)

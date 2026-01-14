package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

/**
 * Data Transfer Object for User.
 * Matches the Firestore document structure exactly.
 * Includes default values for Firestore's toObject() mapping.
 */
data class UserDto(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("email") @set:PropertyName("email") var email: String? = null,
    @get:PropertyName("phone") @set:PropertyName("phone") var phone: String? = null,
    @get:PropertyName("gender") @set:PropertyName("gender") var gender: String = "",
    @get:PropertyName("status") @set:PropertyName("status") var status: String = "",
    @get:PropertyName("verificationLevel") @set:PropertyName("verificationLevel") var verificationLevel: Int = 0,
    @get:PropertyName("createdAt") @set:PropertyName("createdAt") @ServerTimestamp var createdAt: Date? = null
)

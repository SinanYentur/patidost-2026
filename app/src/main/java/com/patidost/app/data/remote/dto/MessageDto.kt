package com.patidost.app.data.remote.dto

import com.google.firebase.Timestamp

/**
 * Data Transfer Object for a message document from Firestore.
 */
data class MessageDto(
    val senderId: String? = null,
    val content: String? = null,
    val timestamp: Timestamp? = null
)

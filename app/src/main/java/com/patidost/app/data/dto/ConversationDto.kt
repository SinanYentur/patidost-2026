package com.patidost.app.data.dto

import com.google.firebase.Timestamp

/**
 * Data Transfer Object for a conversation document from Firestore.
 * This class directly maps to the structure of the data in the database.
 */
data class ConversationDto(
    val participants: List<String>? = null,
    val lastMessage: String? = null,
    val lastMessageTimestamp: Timestamp? = null
)

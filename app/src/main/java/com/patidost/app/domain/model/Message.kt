package com.patidost.app.domain.model

import java.util.Date

data class Message(
    val id: String,
    val conversationId: String,
    val senderId: String,
    val content: String,
    val timestamp: Date,
    val isFromCurrentUser: Boolean
)

package com.patidost.app.domain.model

import java.util.Date

data class Conversation(
    val id: String,
    val participants: List<String>, // List of user IDs
    val lastMessage: String,
    val lastMessageTimestamp: Date,
    val unreadMessageCount: Int,
    val otherUserAvatarUrl: String, // The avatar of the other user in a 1-on-1 chat
    val otherUserName: String // The name of the other user
)

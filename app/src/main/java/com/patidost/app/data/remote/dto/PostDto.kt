
package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class PostDto(
    val id: String = "",
    val ownerId: String = "",
    val petId: String = "",
    val text: String = "",
    val mediaUrl: String? = null,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    @ServerTimestamp
    val createdAt: Date? = null
)

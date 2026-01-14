package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

/**
 * Data Transfer Object for Report.
 * Matches the Firestore document structure exactly.
 */
data class ReportDto(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("targetType") @set:PropertyName("targetType") var targetType: String = "",
    @get:PropertyName("targetId") @set:PropertyName("targetId") var targetId: String = "",
    @get:PropertyName("reporterId") @set:PropertyName("reporterId") var reporterId: String = "",
    @get:PropertyName("reason") @set:PropertyName("reason") var reason: String = "",
    @get:PropertyName("createdAt") @set:PropertyName("createdAt") @ServerTimestamp var createdAt: Date? = null
)

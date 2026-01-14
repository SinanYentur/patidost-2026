package com.patidost.app.domain.model.economy

import java.time.Instant

/**
 * Represents a user's subscription status.
 * This is a core entity of the economy domain, independent of any specific store (Google/Apple).
 */
data class Subscription(
    val userId: String,
    val type: SubscriptionType,
    val status: SubscriptionStatus,
    val validUntil: Instant?,
    val startedAt: Instant
)

enum class SubscriptionType {
    FREE,
    GOLD_MONTHLY,
    GOLD_SEMI_ANNUAL,
    GOLD_ANNUAL
}

enum class SubscriptionStatus {
    ACTIVE,
    INACTIVE,
    GRACE_PERIOD,
    PAUSED,
    CANCELED
}

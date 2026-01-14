package com.patidost.app.domain.model

import java.util.concurrent.TimeUnit

/**
 * Defines user actions that can be rate-limited.
 * Each action has a specific cooldown period.
 */
enum class LimitableAction(val cooldownMillis: Long) {
    POST_COMMENT(TimeUnit.SECONDS.toMillis(60)),
    CREATE_FEED_POST(TimeUnit.MINUTES.toMillis(5)),
    // Add other limitable actions here
}

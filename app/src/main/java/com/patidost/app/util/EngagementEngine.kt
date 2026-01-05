package com.patidost.app.util

import com.patidost.app.domain.model.User

/**
 * EngagementEngine - V10000.28000 Silent Marketing Edition.
 * Rule 102: Simple, humble, and value-driven.
 * RVWL: Smart, non-intrusive monetization triggers based on user behavior.
 */
object EngagementEngine {

    /**
     * Determines if a prestige offer should be shown.
     * Rule: Never interrupt the user. Show only when value is maximized.
     */
    fun shouldShowPrestigeOffer(user: User, actionCount: Int): Boolean {
        // High quality engagement check
        return user.trustScore > 70 && actionCount > 5
    }

    /**
     * Calculates the "Social Merit" bonus.
     * Rule: Reward nobility, not just payment.
     */
    fun calculateSocialMerit(successfulAdoptions: Int): Int {
        return (successfulAdoptions * 5).coerceAtMost(50)
    }
}

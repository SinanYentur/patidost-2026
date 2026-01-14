package com.patidost.app.domain.manager

import com.patidost.app.domain.model.LimitableAction

/**
 * Defines the contract for a central rate limiting authority.
 * This ensures that user actions can be monitored and controlled to prevent abuse.
 */
interface RateLimiter {

    /**
     * Checks if a specific action can be performed by a user.
     * @param action The action to check.
     * @return `true` if the action is allowed, `false` if it is on cooldown.
     */
    fun canPerform(action: LimitableAction): Boolean

    /**
     * Records that an action has been successfully performed.
     * This should be called only after a successful operation to start the cooldown timer.
     * @param action The action that was performed.
     */
    fun recordAction(action: LimitableAction)

    /**
     * Gets the remaining cooldown time for a specific action in milliseconds.
     * @param action The action to check.
     * @return The remaining cooldown in milliseconds, or 0 if the action is not on cooldown.
     */
    fun getRemainingCooldown(action: LimitableAction): Long
}

package com.patidost.app.data.manager

import com.patidost.app.domain.manager.RateLimiter
import com.patidost.app.domain.model.LimitableAction
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RateLimiterImpl @Inject constructor() : RateLimiter {

    private val timestamps = ConcurrentHashMap<String, Long>()

    override fun canPerform(action: LimitableAction): Boolean {
        val lastTime = timestamps[action.name]
        val currentTime = System.currentTimeMillis()

        if (lastTime == null) {
            return true
        }

        return (currentTime - lastTime) >= action.cooldownMillis
    }

    override fun recordAction(action: LimitableAction) {
        timestamps[action.name] = System.currentTimeMillis()
    }

    override fun getRemainingCooldown(action: LimitableAction): Long {
        val lastTime = timestamps[action.name] ?: return 0
        val currentTime = System.currentTimeMillis()
        val elapsedTime = currentTime - lastTime
        val remaining = action.cooldownMillis - elapsedTime
        return if (remaining > 0) remaining else 0
    }
}

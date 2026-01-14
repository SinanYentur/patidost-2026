package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.economy.Subscription
import com.patidost.app.domain.model.economy.Wallet
import kotlinx.coroutines.flow.Flow

/**
 * The contract for all economy-related data operations.
 */
interface EconomyRepository {
    /**
     * Gets the current user's wallet information.
     */
    fun getWallet(): Flow<Resource<Wallet>>

    /**
     * Gets the current user's subscription status.
     */
    fun getSubscription(): Flow<Resource<Subscription>>
}

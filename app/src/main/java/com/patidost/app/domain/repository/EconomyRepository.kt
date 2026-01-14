package com.patidost.app.domain.repository

import android.app.Activity
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

    /**
     * Attempts to purchase a subscription product.
     * @param activity The activity from which the billing flow will be launched.
     * @param productId The ID of the product from the store (e.g., "gold_monthly").
     * @return A resource indicating the success or failure of the server-side validation.
     */
    suspend fun purchaseSubscription(activity: Activity, productId: String): Resource<Unit>

    /**
     * Atomically gives a "pati" to a pet.
     * This action is handled by a secure backend function.
     * @param targetPetId The ID of the pet receiving the pati.
     * @param amount The number of pati to give.
     * @return A resource indicating the success or failure of the transaction.
     */
    suspend fun givePati(targetPetId: String, amount: Int = 1): Resource<Unit>
}

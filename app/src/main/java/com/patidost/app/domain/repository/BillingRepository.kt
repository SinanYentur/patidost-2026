package com.patidost.app.domain.repository

import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ 2026 MONETIZATION GATEWAY
 * Standard: Play Billing 6.0+
 */
interface BillingRepository {
    val subscriptionStatus: Flow<SubscriptionStatus>
    val products: Flow<List<BillingProduct>>
    
    suspend fun startConnection()
    suspend fun purchaseProduct(productId: String): DomainResult<Unit>
    suspend fun restorePurchases(): DomainResult<Unit>
}

sealed class SubscriptionStatus {
    object Free : SubscriptionStatus()
    data class Premium(val expiryDate: Long) : SubscriptionStatus()
    object GracePeriod : SubscriptionStatus()
}

data class BillingProduct(
    val id: String,
    val title: String,
    val price: String,
    val type: String // SUBSCRIPTION or INAPP
)

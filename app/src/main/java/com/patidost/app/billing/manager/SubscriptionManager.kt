package com.patidost.app.billing.manager

import android.content.Context
import com.android.billingclient.api.*
import com.patidost.app.billing.validator.PurchasesValidator
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val billingClient: BillingClient,
    private val purchasesValidator: PurchasesValidator
) {
    enum class GoldTier(val sku: String, val durationMonths: Int, val bonusPoints: Int) {
        BRONZE("gold_3m", 3, 15),
        SILVER("gold_6m", 6, 40),
        GOLD("gold_12m", 12, 100)
    }

    private val _subscriptionState = MutableStateFlow<SubscriptionState>(SubscriptionState.NoSubscription)
    val subscriptionState: StateFlow<SubscriptionState> = _subscriptionState

    fun canPerformSuperLike(userPoints: Int): Boolean = userPoints >= 1

    sealed class SubscriptionState {
        object NoSubscription : SubscriptionState()
        data class Active(val tier: GoldTier, val expiresAt: Instant) : SubscriptionState()
    }
}

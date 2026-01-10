package com.patidost.app.data.repository

import android.content.Context
import com.android.billingclient.api.*
import com.patidost.app.domain.repository.BillingProduct
import com.patidost.app.domain.repository.BillingRepository
import com.patidost.app.domain.repository.SubscriptionStatus
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.util.AppError
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillingRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BillingRepository, PurchasesUpdatedListener {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    private val billingClient = BillingClient.newBuilder(context)
        .setListener(this)
        .enablePendingPurchases(
            PendingPurchasesParams.newBuilder()
                .enableOneTimeProducts()
                .enablePrepaidPlans()
                .build()
        )
        .build()

    private val _subscriptionStatus = MutableStateFlow<SubscriptionStatus>(SubscriptionStatus.Free)
    override val subscriptionStatus = _subscriptionStatus.asStateFlow()

    private val _products = MutableStateFlow<List<BillingProduct>>(emptyList())
    override val products = _products.asStateFlow()

    init {
        scope.launch {
            startConnection()
        }
    }

    override suspend fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    queryPurchases()
                }
            }

            override fun onBillingServiceDisconnected() {
                // Retry logic
            }
        })
    }

    private fun queryPurchases() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(params) { _, purchases ->
            processPurchases(purchases)
        }
    }

    override suspend fun purchaseProduct(productId: String): DomainResult<Unit> = DomainResult.Success(Unit)

    override suspend fun restorePurchases(): DomainResult<Unit> {
        queryPurchases()
        return DomainResult.Success(Unit)
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            processPurchases(purchases)
        }
    }

    private fun processPurchases(purchases: List<Purchase>) {
        purchases.forEach { purchase ->
            if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                _subscriptionStatus.value = SubscriptionStatus.Premium(System.currentTimeMillis())
            }
        }
    }
}

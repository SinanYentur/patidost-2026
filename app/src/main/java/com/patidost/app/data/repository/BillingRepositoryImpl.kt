package com.patidost.app.data.repository

import android.content.Context
import com.android.billingclient.api.*
import com.patidost.app.domain.repository.BillingProduct
import com.patidost.app.domain.repository.BillingRepository
import com.patidost.app.domain.repository.SubscriptionStatus
import com.patidost.app.domain.util.AppError
import com.patidost.app.domain.util.DomainResult
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
        .enablePendingPurchases()
        .build()

    private val _subscriptionStatus = MutableStateFlow<SubscriptionStatus>(SubscriptionStatus.Free)
    override val subscriptionStatus = _subscriptionStatus.asStateFlow()

    private val _products = MutableStateFlow<List<BillingProduct>>(emptyList())
    override val products = _products.asStateFlow()

    init {
        startConnection()
    }

    override suspend fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Timber.i("🛡️ Billing: Connected")
                    queryPurchases()
                    queryProductDetails()
                }
            }

            override fun onBillingServiceDisconnected() {
                Timber.w("🛡️ Billing: Disconnected. Retrying...")
                // Exponential backoff logic can be added here
            }
        })
    }

    private fun queryPurchases() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(params) { result, purchases ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                processPurchases(purchases)
            }
        }
    }

    private fun queryProductDetails() {
        val productList = listOf(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("premium_monthly")
                .setProductType(BillingClient.ProductType.SUBS)
                .build()
        )

        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()

        billingClient.queryProductDetailsAsync(params) { result, detailsList ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                _products.value = detailsList.map {
                    BillingProduct(
                        id = it.productId,
                        title = it.title,
                        price = it.subscriptionOfferDetails?.firstOrNull()?.pricingPhases?.pricingPhaseList?.firstOrNull()?.formattedPrice ?: "N/A",
                        type = "SUBSCRIPTION"
                    )
                }
            }
        }
    }

    override suspend fun purchaseProduct(productId: String): DomainResult<Unit> {
        // Purchase logic with activity context requirement will be handled via ViewModel/UI bridge
        return DomainResult.Success(Unit) 
    }

    override suspend fun restorePurchases(): DomainResult<Unit> {
        queryPurchases()
        return DomainResult.Success(Unit)
    }

    override fun onPurchatedUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            processPurchases(purchases)
        }
    }

    private fun processPurchases(purchases: List<Purchase>) {
        purchases.forEach { purchase ->
            if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                if (!purchase.isAcknowledged) {
                    val acknowledgeParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()
                    billingClient.acknowledgePurchase(acknowledgeParams) { result ->
                        if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                            Timber.i("🛡️ Billing: Purchase Acknowledged")
                            _subscriptionStatus.value = SubscriptionStatus.Premium(System.currentTimeMillis())
                        }
                    }
                } else {
                    _subscriptionStatus.value = SubscriptionStatus.Premium(System.currentTimeMillis())
                }
            }
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        onPurchatedUpdated(billingResult, purchases)
    }
}

package com.patidost.app.data.billing

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PurchasesUpdatedListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class BillingClientWrapper(context: Context) {

    private val _purchases = MutableStateFlow<List<com.android.billingclient.api.Purchase>>(emptyList())
    val purchases = _purchases.asStateFlow()

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            _purchases.value = purchases
        } else {
            // Handle an error caused by a user cancelling the purchase flow.
            Timber.w("Purchase failed with response code: ${billingResult.responseCode}")
        }
    }

    private val billingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    Timber.d("BillingClient is ready.")
                    // The BillingClient is ready. You can query purchases here.
                }
            }
            override fun onBillingServiceDisconnected() {
                Timber.w("BillingClient service disconnected. Trying to reconnect...")
                // Try to restart the connection on the next request.
                // This is a simplified retry policy.
                startConnection()
            }
        })
    }
}

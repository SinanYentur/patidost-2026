package com.patidost.app.billing

import android.content.Context
import com.android.billingclient.api.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillingClientProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var billingClient: BillingClient? = null

    fun getOrCreateClient(listener: PurchasesUpdatedListener): BillingClient {
        return billingClient ?: BillingClient.newBuilder(context)
            .setListener(listener)
            .enablePendingPurchases(PendingPurchasesParams.newBuilder().enableOneTimeProducts().build())
            .build().also { billingClient = it }
    }
}

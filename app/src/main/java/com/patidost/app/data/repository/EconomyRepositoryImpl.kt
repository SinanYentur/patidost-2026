package com.patidost.app.data.repository

import android.app.Activity
import com.google.firebase.functions.FirebaseFunctions
import com.patidost.app.core.util.Resource
import com.patidost.app.data.billing.BillingClientWrapper
import com.patidost.app.domain.model.economy.Subscription
import com.patidost.app.domain.model.economy.Wallet
import com.patidost.app.domain.repository.EconomyRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EconomyRepositoryImpl @Inject constructor(
    private val billingClientWrapper: BillingClientWrapper,
    private val functions: FirebaseFunctions
) : EconomyRepository {

    override fun getWallet(): Flow<Resource<Wallet>> {
        return flow { emit(Resource.Error(UiText.DynamicString("Cüzdan özelliği geçici olarak devre dışıdır."))) }
    }

    override fun getSubscription(): Flow<Resource<Subscription>> {
        return flow { emit(Resource.Error(UiText.DynamicString("Not yet implemented"))) }
    }

    override suspend fun purchaseSubscription(activity: Activity, productId: String): Resource<Unit> {
        val productDetails = billingClientWrapper.productDetails.firstOrNull()?.find { it.productId == productId }
            ?: return Resource.Error(UiText.DynamicString("Product not found."))

        billingClientWrapper.launchPurchaseFlow(activity, productDetails)
        
        return Resource.Success(Unit)
    }

    override suspend fun givePati(targetPetId: String, amount: Int): Resource<Unit> {
        // ANAYASAL ASKIYA ALMA NOTU: Bu özellik, Blaze planına geçilene kadar devre dışı bırakılmıştır.
        return Resource.Error(UiText.DynamicString("Pati verme özelliği geçici olarak mevcut değildir."))
    }
}

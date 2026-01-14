package com.patidost.app.domain.usecase.economy

import android.app.Activity
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.EconomyRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class PurchaseGoldUseCase @Inject constructor(
    private val repository: EconomyRepository
) {
    suspend operator fun invoke(activity: Activity, productId: String): Resource<Unit> {
        if (productId.isBlank() || !productId.startsWith("gold_")) {
            return Resource.Error(UiText.DynamicString("Invalid product ID."))
        }
        return repository.purchaseSubscription(activity, productId)
    }
}

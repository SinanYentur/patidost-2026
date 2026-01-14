package com.patidost.app.domain.usecase.economy

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.EconomyRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class PurchaseGoldUseCase @Inject constructor(
    private val repository: EconomyRepository
) {
    suspend operator fun invoke(productId: String, storeToken: String): Resource<Unit> {
        if (productId.isBlank() || !productId.startsWith("gold_")) {
            return Resource.Error(UiText.DynamicString("Invalid product ID."))
        }
        if (storeToken.isBlank()) {
            return Resource.Error(UiText.DynamicString("Store token is missing."))
        }
        return repository.purchaseSubscription(productId, storeToken)
    }
}

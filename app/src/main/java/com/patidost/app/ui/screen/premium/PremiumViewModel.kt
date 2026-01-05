package com.patidost.app.ui.screen.premium

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.repository.BillingRepository
import com.patidost.app.domain.repository.SubscriptionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PremiumViewModel @Inject constructor(
    private val billingRepository: BillingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val products = billingRepository.products.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val subscriptionStatus = billingRepository.subscriptionStatus.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SubscriptionStatus.Free
    )

    fun onPurchaseClick(productId: String) {
        viewModelScope.launch {
            billingRepository.purchaseProduct(productId)
        }
    }

    fun onRestoreClick() {
        viewModelScope.launch {
            billingRepository.restorePurchases()
        }
    }
}

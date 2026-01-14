package com.patidost.app.presentation.ui.screen.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.economy.PurchaseGoldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoldViewModel @Inject constructor(
    private val purchaseGoldUseCase: PurchaseGoldUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoldUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPlans()
    }

    fun onEvent(event: GoldPurchaseEvent) {
        when (event) {
            is GoldPurchaseEvent.SelectPlan -> {
                _uiState.update { it.copy(selectedPlan = event.plan, purchaseError = null) }
            }
            is GoldPurchaseEvent.Purchase -> {
                purchase()
            }
        }
    }

    private fun purchase() {
        val planToPurchase = _uiState.value.selectedPlan ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, purchaseError = null) }

            // In a real app, this token would come from the Google Play Billing library
            val fakeStoreToken = "fake_store_token_for_${planToPurchase.productId}"

            val result = purchaseGoldUseCase(planToPurchase.productId, fakeStoreToken)

            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, isPurchaseSuccess = true) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, purchaseError = result.message) }
                }
                is Resource.Loading -> { /* Handled by isLoading state */ }
            }
        }
    }

    private fun loadPlans() {
        // In a real app, these would come from a remote config or a specific use case
        val samplePlans = listOf(
            SubscriptionPlanUiModel("gold_monthly", "1 Ay", "Aylık Gold Üyelik", "$9.99"),
            SubscriptionPlanUiModel("gold_semi_annual", "6 Ay", "6 Aylık Gold Üyelik", "$49.99"),
            SubscriptionPlanUiModel("gold_annual", "12 Ay", "Yıllık Gold Üyelik", "$89.99")
        )
        _uiState.update { it.copy(plans = samplePlans) }
    }
}

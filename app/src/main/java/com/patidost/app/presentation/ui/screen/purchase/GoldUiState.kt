package com.patidost.app.presentation.ui.screen.purchase

import com.patidost.app.presentation.ui.util.UiText

data class GoldUiState(
    val plans: List<SubscriptionPlanUiModel> = emptyList(),
    val selectedPlan: SubscriptionPlanUiModel? = null,
    val isLoading: Boolean = false,
    val isPurchaseSuccess: Boolean = false,
    val purchaseError: UiText? = null
)

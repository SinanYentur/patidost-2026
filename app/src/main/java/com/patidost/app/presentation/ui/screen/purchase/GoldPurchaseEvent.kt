package com.patidost.app.presentation.ui.screen.purchase

sealed interface GoldPurchaseEvent {
    data class SelectPlan(val plan: SubscriptionPlanUiModel) : GoldPurchaseEvent
    object Purchase : GoldPurchaseEvent
}

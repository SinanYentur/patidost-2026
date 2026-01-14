package com.patidost.app.presentation.ui.screen.purchase

import android.app.Activity

sealed interface GoldPurchaseEvent {
    data class SelectPlan(val plan: SubscriptionPlanUiModel) : GoldPurchaseEvent
    data class Purchase(val activity: Activity) : GoldPurchaseEvent
}

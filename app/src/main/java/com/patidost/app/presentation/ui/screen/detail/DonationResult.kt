package com.patidost.app.presentation.ui.screen.detail

import com.patidost.app.presentation.ui.util.UiText

sealed interface DonationResult {
    data object Success : DonationResult
    data class Error(val message: UiText) : DonationResult
}

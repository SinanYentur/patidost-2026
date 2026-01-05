package com.patidost.app.ui.screen.profile

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Pet Reminder Screen - V47.65 Surgical Split Fix.
 * RVWL: Synchronized with ProfileComponents.kt physical file.
 */
@Composable
fun PetReminderScreen(
    viewModel: ProfileViewModel,
    onBack: () -> Unit
) {
    // Rule 97: Corrected parameter pass to ReminderContent
    ReminderContent(
        onAddReminder = { viewModel.addReminder(it) }
    )
    
    // Rule 92: Back navigation handled via custom UI if needed
}

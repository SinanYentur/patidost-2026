package com.patidost.app.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.theme.PatiGold

/**
 * Pet Reminder Components - V52.00 Rule 94.2 Verified.
 * RVWL: Unique physical component file for PetReminderScreen.
 */
@Composable
fun ReminderContent(
    onAddReminder: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hatırlatıcılar", style = MaterialTheme.typography.headlineMedium, color = PatiGold)
    }
}

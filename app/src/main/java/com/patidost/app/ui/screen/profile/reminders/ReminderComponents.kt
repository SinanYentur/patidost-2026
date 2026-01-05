package com.patidost.app.ui.screen.profile.reminders

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.theme.PatiGold

/**
 * Reminder Components - V2001.25 Hierarchical Migration.
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

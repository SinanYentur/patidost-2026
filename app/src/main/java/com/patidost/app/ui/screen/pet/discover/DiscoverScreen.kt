package com.patidost.app.ui.screen.pet.discover

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.patidost.app.ui.component.core.SovereignScreenState

/**
 * 🛡️ DiscoverScreen - V10000.70016 Restoration Seal.
 * Rule 420: Restored from Purge to fix NavGraph resolution.
 */
@Composable
fun DiscoverScreen(
    onPetClick: (String) -> Unit
) {
    SovereignScreenState(
        isLoading = false,
        isEmpty = false
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Keşfet: Yakında yeni dostlar eklenecek!", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

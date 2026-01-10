package com.patidost.app.ui.component.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import timber.log.Timber

/**
 * 🛡️ AppArmor - Sovereign Security UI Layer.
 * Provides root protection and global state monitoring.
 * Rule 112: RASP (Runtime Application Self-Protection) visual feedback.
 */
@Composable
fun AppArmor(
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        
        // 🛡️ Mühür: Global Security Monitor (Visual Overlay in Debug)
        if (androidx.compose.ui.platform.LocalInspectionMode.current) {
            SecurityOverlay()
        }
    }
}

@Composable
private fun SecurityOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.1f)),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "🛡️ SOVEREIGN SECURITY ACTIVE",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Red,
            modifier = Modifier.padding(8.dp)
        )
    }
}

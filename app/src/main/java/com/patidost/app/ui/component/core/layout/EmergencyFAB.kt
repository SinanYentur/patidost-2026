package com.patidost.app.ui.component.core.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 🛡️ EmergencyFAB - Sovereign Alert System.
 * Rule 420: Standardized icons and library synchronization.
 * V10000.70017: Fixed Material Icons path and dependency resolution.
 */
@Composable
fun EmergencyFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Emergency Alert"
        )
    }
}

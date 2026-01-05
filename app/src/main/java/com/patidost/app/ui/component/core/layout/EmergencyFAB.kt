package com.patidost.app.ui.component.core.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * EmergencyFAB - V10000.25000 Sovereign Safety.
 * Rule 125: Global emergency access point.
 * RVWL: High-visibility floating button for immediate veterinary assistance.
 */
@Composable
fun EmergencyFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeFloatingActionButton(
        onClick = onClick,
        containerColor = Color.Red,
        contentColor = Color.White,
        modifier = modifier
            .padding(16.dp)
            .size(64.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Emergency,
            contentDescription = "ACİL VETERİNER YARDIMI",
            modifier = Modifier.size(32.dp)
        )
    }
}

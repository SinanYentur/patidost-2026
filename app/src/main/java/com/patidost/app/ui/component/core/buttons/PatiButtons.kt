package com.patidost.app.ui.component.core.buttons

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Core Pati Buttons - V2001.60 Athena Protocol.
 */
@Composable
fun PremiumPatiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF9C27B0) // Athena Purple SSOT
        )
    ) {
        Text(text = text, color = Color.White)
    }
}

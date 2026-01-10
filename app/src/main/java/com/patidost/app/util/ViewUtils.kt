package com.patidost.app.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * 🛡️ ViewUtils - V10000.70036 Sovereign Visual Engine.
 * Rule 102: Athena Protocol Snowy A+++ Gradient Implementation.
 */
fun calculateSnowyGradient(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE3F2FD).copy(alpha = 0.8f), // Snowy Blue base
            Color.White.copy(alpha = 0.9f)        // Clean White finish
        )
    )
}

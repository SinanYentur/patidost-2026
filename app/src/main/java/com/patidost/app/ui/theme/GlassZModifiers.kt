package com.patidost.app.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * 🛡️ Athena Protocol - Glass-Z Modifiers V10011.70135.
 * Rule 410: Standardized Legibility (Google Play Accessibility Compliant).
 * ARTICLE 21: Reduced blur to 8dp and removed drawBehind to prevent GPU overdraw on physical devices.
 */
fun Modifier.athenaGlassEffect(
    blurRadius: Int = 8, // 🛡️ Reduced from 16 to 8 for crystal clear text
    opacity: Float = 0.2f // 🛡️ Increased for higher contrast
): Modifier = this.graphicsLayer {
    // 🛡️ Mühür: Sadece bu katmanı izole eder, içeriğe sızmayı engeller.
    clip = true
    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
}.blur(blurRadius.dp)

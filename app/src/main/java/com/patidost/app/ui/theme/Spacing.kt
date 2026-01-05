package com.patidost.app.ui.theme

import androidx.compose.ui.unit.dp

/**
 * 🚀 PATIDOST SOVEREIGN SPACING SYSTEM v3.0
 * Based on 8dp Grid Mathematical Proof.
 */
object Space {
    val xs = 4.dp   // 0.5× base (micro spacing)
    val sm = 8.dp   // 1× base (small spacing)
    val md = 16.dp  // 2× base (medium spacing)
    val lg = 24.dp  // 3× base (large spacing)
    val xl = 32.dp  // 4× base (extra large)
    val xxl = 48.dp // 6× base (hero spacing)
    
    // Component-specific aliases
    val screen = md
    val card = md
    val button = sm
    val icon = xs
}

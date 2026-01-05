package com.patidost.app.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * Athena Protocol - Glass-Z Modifiers.
 * V10000.19400 Premium Visual Seal.
 */
fun Modifier.athenaGlassEffect(
    blurRadius: Int = 20,
    opacity: Float = 0.1f
): Modifier = this
    .graphicsLayer {
        clip = true
    }
    .blur(blurRadius.dp)
    .drawWithContent {
        drawContent()
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = opacity),
                    Color.White.copy(alpha = opacity * 0.5f)
                )
            ),
            blendMode = BlendMode.Overlay
        )
    }

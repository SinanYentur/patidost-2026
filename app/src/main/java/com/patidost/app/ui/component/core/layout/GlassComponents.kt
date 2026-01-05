package com.patidost.app.ui.component.core.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Core Glass Layouts - V2001.55 Athena Protocol.
 */
@Composable
fun GlassBox(
    modifier: Modifier = Modifier,
    blurRadius: Dp = 15.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .blur(blurRadius)
            .background(Color.White.copy(alpha = 0.05f))
    ) {
        content()
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.1f)),
        content = content
    )
}

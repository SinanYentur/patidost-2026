package com.patidost.app.ui.component.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Core Glass Modifiers - V2001.50 Athena Protocol.
 */
fun Modifier.glassEffect(blurRadius: Dp = 30.dp) = this.then(
    Modifier.blur(blurRadius)
)

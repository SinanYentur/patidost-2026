package com.patidost.app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.theme.*

/**
 * Premium Glass Effect Modifier - Mimar V44 Standard.
 * RVWL: Synchronized with Vision Assets 1 and 5.
 */
fun Modifier.patidostGlassCard() = this
    .clip(RoundedCornerShape(20.dp))
    .background(Brush.verticalGradient(listOf(GlassWhite, Color(0x05FFFFFF))))
    .border(
        width = 0.5.dp, 
        brush = Brush.verticalGradient(listOf(GlassStroke, Color.Transparent)), 
        shape = RoundedCornerShape(20.dp)
    )

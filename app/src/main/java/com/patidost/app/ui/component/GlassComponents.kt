package com.patidost.app.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.theme.*

/**
 * Patidost Premium UI Kit - V45 Referans Standartları.
 */

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    isDark: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(if (isDark) SpaceVoid.copy(alpha = 0.7f) else GlassWhite)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(GlassStroke, Color.Transparent)
                ),
                shape = RoundedCornerShape(28.dp)
            )
    ) {
        Box(modifier = Modifier.matchParentSize().blur(20.dp))
        Box(modifier = Modifier.padding(16.dp)) { content() }
    }
}

/**
 * Modifier Extension: glassZ
 * Ekranlardaki mevcut çağrıları karşılamak ve derinlik katmak için.
 */
fun Modifier.glassZ(
    blur: Dp = 10.dp,
    alpha: Float = 0.15f,
    shadowElevation: Dp = 0.dp // Legacy support
): Modifier = this
    .clip(RoundedCornerShape(24.dp))
    .background(Color.White.copy(alpha = alpha))
    .border(
        width = 1.dp,
        brush = Brush.verticalGradient(listOf(Color.White.copy(alpha = 0.3f), Color.Transparent)),
        shape = RoundedCornerShape(24.dp)
    )
    .blur(blur)

/**
 * Referans 1: Altın Aura (Glow) Efekti
 */
fun Modifier.goldenAura(radius: Dp = 12.dp): Modifier = this.drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint().asFrameworkPaint()
        paint.color = Color.Transparent.toArgb()
        paint.setShadowLayer(
            radius.toPx(),
            0f, 0f,
            PatiGold.copy(alpha = 0.5f).toArgb()
        )
        canvas.nativeCanvas.drawRoundRect(
            0f, 0f, size.width, size.height,
            24.dp.toPx(), 24.dp.toPx(),
            paint
        )
    }
}

@Composable
fun PremiumPatiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .goldenAura(),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(PatiGold, PatiGoldDark))),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = SpaceDeep)
        }
    }
}

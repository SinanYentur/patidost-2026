package com.patidost.app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.theme.GlassBorder
import com.patidost.app.ui.theme.PatiGold
import com.patidost.app.ui.theme.athenaGlassEffect

/**
 * 🛡️ GlassComponents - V10011.70133 Sovereign Visual Seal.
 * Rule 410: Layered Blur Implementation (Content stays crisp).
 * ARTICLE 20: Separated blur layer from content layer to ensure legibility.
 */

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
    ) {
        // 🛡️ Katman 1: Bulanıklık ve Arka Plan (Content'i etkilemez)
        Box(
            modifier = Modifier
                .matchParentSize()
                .athenaGlassEffect() // Sadece bu kutuyu bulandırır
                .background(Color.White.copy(alpha = 0.12f))
        )
        
        // 🛡️ Katman 2: Kenarlık
        Box(
            modifier = Modifier
                .matchParentSize()
                .border(1.dp, GlassBorder, RoundedCornerShape(24.dp))
        )

        // 🛡️ Katman 3: Tertemiz İçerik (Metinler burada)
        Box(modifier = Modifier, content = content)
    }
}

@Composable
fun PremiumPatiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = PatiGold,
    contentColor: Color = Color.Black
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 0.dp
        )
    ) {
        Text(
            text = text.uppercase(), 
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

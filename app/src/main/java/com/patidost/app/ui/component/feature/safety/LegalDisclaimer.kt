package com.patidost.app.ui.component.feature.safety

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * LegalDisclaimer - V10000.15100 Rule 113 Compliance.
 * Mandatory for 12+ (Teen) rating on Play Store.
 */
@Composable
fun MedicalAdviceDisclaimer(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Red.copy(alpha = 0.1f),
        shape = MaterialTheme.shapes.small
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "⚠️ TIBBİ UYARI",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Text(
                text = "Bu içerik kullanıcı tarafından oluşturulmuştur ve veteriner tavsiyesi değildir. Acil durumlar için lütfen derhal bir kliniğe başvurun.",
                fontSize = 11.sp,
                lineHeight = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}

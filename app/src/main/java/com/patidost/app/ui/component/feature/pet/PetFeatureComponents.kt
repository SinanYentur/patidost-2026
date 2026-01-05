package com.patidost.app.ui.component.feature.pet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.domain.model.Pet
import com.patidost.app.ui.component.core.layout.GlassCard
import com.patidost.app.ui.theme.PatiGold

/**
 * Pet Feature Components - V2001.65 Hierarchical Migration.
 * RVWL: Shared components used across multiple Pet sub-features.
 */
@Composable
fun PetCard(
    pet: Pet,
    onClick: () -> Unit
) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pet.name, color = PatiGold, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = pet.breed, color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onClick) {
                Text("Detaylar")
            }
        }
    }
}

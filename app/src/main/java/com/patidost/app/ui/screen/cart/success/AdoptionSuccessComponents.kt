package com.patidost.app.ui.screen.cart.success

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.PatiGold

/**
 * Adoption Success Components - V2001.05 Hierarchical Migration.
 */
@Composable
fun SuccessContent(
    onHomeClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tebrikler!", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = PatiGold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Yeni dostun seni bekliyor.", color = androidx.compose.ui.graphics.Color.White)
        Spacer(modifier = Modifier.height(48.dp))
        PremiumPatiButton(
            text = "Ana Sayfaya Dön",
            onClick = onHomeClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

package com.patidost.app.ui.screen.cart.success

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.theme.PatiGold

/**
 * Adoption Success Screen - V10000.17400 Sovereign Victory.
 * Rule 102: Simple, humble, but powerful.
 * RVWL: Unified Success display with Digital Receipt (SPDX) export.
 */
@Composable
fun AdoptionSuccessScreen(
    petName: String,
    onDownloadReceipt: () -> Unit,
    onFinish: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GlassCard(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = PatiGold
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Yeni Bir Başlangıç!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PatiGold
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "$petName artık senin ailenin bir parçası. Bu asil kararın için teşekkür ederiz.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(32.dp))

                // --- SOVEREIGN PROOF SECTION ---
                OutlinedButton(
                    onClick = onDownloadReceipt,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PatiGold)
                ) {
                    Icon(Icons.Default.Download, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Dijital Sahiplenme Makbuzu")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onFinish,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PatiGold)
                ) {
                    Text("Ana Sayfaya Dön")
                }
            }
        }
    }
}

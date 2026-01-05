package com.patidost.app.ui.screen.cart.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.*

/**
 * Cart Main Screen - V10001.00000 Snowy A+++ Edition.
 * REPLICA: Synchronized with the premium snowy/light glass UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    userPatiPoints: Int,
    onAdoptClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(calculateSnowyGradient())
    ) {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Sahiplenme Sepeti", color = TextDark, fontWeight = FontWeight.Black) },
                    actions = {
                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .background(GlassMedium, androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Star, null, tint = PatiGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("$userPatiPoints", color = PatiGold, fontWeight = FontWeight.Bold)
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlassCard(modifier = Modifier.padding(24.dp)) {
                    Column(
                        modifier = Modifier.padding(24.dp), 
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Pets, 
                            contentDescription = null, 
                            modifier = Modifier.size(64.dp), 
                            tint = PatiGold
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Text(
                            text = "Dostluğa Giden Son Adım", 
                            style = MaterialTheme.typography.headlineSmall,
                            color = TextDark,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text(
                            text = "Sahiplenme işleminiz Google Play üzerinden güvenle tescil edilecektir.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextGray,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        PremiumPatiButton(
                            text = "Şimdi Sahiplen",
                            onClick = onAdoptClick,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

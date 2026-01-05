package com.patidost.app.ui.screen.pet.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.theme.*
import com.patidost.app.util.SovereignHapticFeedback

/**
 * Discover Screen - V10001.00000 Snowy A+++ Edition.
 * REPLICA: Final polished version with GPU-optimized Glassmorphism.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(
    onAiSearchClick: () -> Unit
) {
    val context = LocalContext.current
    val hapticFeedback = LocalHapticFeedback.current
    val sovereignHaptic = remember { SovereignHapticFeedback(context, hapticFeedback) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(calculateSnowyGradient())
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Yeni Dostunu Keşfet", color = TextDark, fontWeight = FontWeight.Black) },
                    actions = {
                        IconButton(onClick = {
                            sovereignHaptic.perform(SovereignHapticFeedback.FeedbackType.MEDIUM)
                            onAiSearchClick()
                        }) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = "AI Search", tint = PatiGold)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(Space.md),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlassCard(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(64.dp), tint = PatiGold)
                            Spacer(modifier = Modifier.height(Space.md))
                            Text("Bana benzeyen dostu bul...", color = TextDark, style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}

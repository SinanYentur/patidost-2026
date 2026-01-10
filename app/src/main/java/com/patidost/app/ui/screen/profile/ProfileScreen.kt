package com.patidost.app.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.component.core.SovereignButton

/**
 * 🛡️ ProfileScreen - V10000.70036 Sovereign Seal.
 * Rule 420: Fixed Experimental Material 3 API opt-in requirement.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", style = MaterialTheme.typography.displayLarge)
                }
            }
            
            Text("Kullanıcı Profili", style = MaterialTheme.typography.headlineMedium)
            
            SovereignButton(
                text = "Ayarlar",
                onClick = { /* Navigate to settings */ }
            )
            
            SovereignButton(
                text = "Çıkış Yap",
                onClick = { /* Sign out logic */ }
            )
        }
    }
}

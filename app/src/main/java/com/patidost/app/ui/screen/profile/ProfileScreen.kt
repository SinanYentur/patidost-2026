package com.patidost.app.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.component.core.SovereignButton

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
            // Placeholder for profile
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
                onClick = { /* TODO */ }
            )
            
            SovereignButton(
                text = "Çıkış Yap",
                onClick = { /* TODO */ }
            )
        }
    }
}

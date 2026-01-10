package com.patidost.app.ui.screen.pet.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.component.core.SovereignScreenState
import com.patidost.app.ui.component.core.SovereignButton

/**
 * 🛡️ PetDetailScreen - V10000.70053 Sovereign Alignment.
 * Rule 310: Physical synchronization with detail sub-package.
 * ARTICLE 4: Complete DNA link between Screen and ViewModel in pet.detail.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dost Detayı") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        SovereignScreenState(
            isLoading = false,
            isEmpty = false
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Dost bilgileri yakında burada olacak.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(24.dp))
                SovereignButton(
                    text = "Geri Dön",
                    onClick = onBackClick
                )
            }
        }
    }
}

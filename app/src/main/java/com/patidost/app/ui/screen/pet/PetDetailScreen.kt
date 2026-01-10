package com.patidost.app.ui.screen.pet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.ui.component.core.SovereignScreenState
import com.patidost.app.ui.component.core.SovereignButton

/**
 * 🛡️ PetDetailScreen - V10000.70069 Sovereign Scaling Seal.
 * Rule 310: Responsive layout and scrolling protection for long descriptions.
 * ARTICLE 12: Tablet optimization (max-width) and vertical scroll support.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = when (val state = uiState) {
                            is PetDetailUiState.Success -> state.pet.name
                            else -> "Dost Detayı"
                        }
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        SovereignScreenState(
            isLoading = uiState is PetDetailUiState.Loading,
            isEmpty = false,
            error = (uiState as? PetDetailUiState.Error)?.message
        ) {
            val pet = (uiState as? PetDetailUiState.Success)?.pet
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(scrollState), // 🛡️ Sızma/Taşma Koruması
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (pet != null) {
                    // 🛡️ Tablet Ölçeklendirmesi (Geniş ekranlarda ortalı tutar)
                    Column(
                        modifier = Modifier.widthIn(max = 600.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tür: ${pet.breed}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = pet.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                
                SovereignButton(
                    text = "Geri Dön",
                    onClick = onBackClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 400.dp) // 🛡️ Tablet Genişlik Kısıtlaması
                )
            }
        }
    }
}

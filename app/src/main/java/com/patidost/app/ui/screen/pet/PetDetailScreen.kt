package com.patidost.app.ui.screen.pet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.R // CORRECTED IMPORT
import com.patidost.app.ui.component.glassZ

/**
 * Pet Detail Screen - 2026 Android 16 (API 36) PRODUCTION Standard.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.pet_detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                modifier = Modifier.glassZ()
            )
        },
        contentWindowInsets = WindowInsets.systemBars 
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState) {
                is PetDetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is PetDetailUiState.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.pet.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = state.pet.description,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { viewModel.adoptPet() },
                            enabled = !state.isAdopted && !state.isAdopting,
                            modifier = Modifier.fillMaxWidth().height(56.dp)
                        ) {
                            if (state.isAdopting) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    if (state.isAdopted) stringResource(R.string.pet_adopted) 
                                    else stringResource(R.string.pet_adopt_now)
                                )
                            }
                        }
                    }
                }
                is PetDetailUiState.Error -> {
                    Text(
                        text = stringResource(state.messageResId), // Sync with localized ID
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

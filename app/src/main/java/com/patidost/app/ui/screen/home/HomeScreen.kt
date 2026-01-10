package com.patidost.app.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.domain.model.Pet
import com.patidost.app.ui.screen.pet.list.PetListViewModel
import com.patidost.app.ui.theme.PatiGold

/**
 * 🛡️ HomeScreen - V10000.70033 Sovereign Hub.
 * Rule 500: Circular Verification with PetListViewModel.
 * Rule 420: Fixed physical path mapping for ViewModel.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPetClick: (String) -> Unit,
    onProfileClick: () -> Unit,
    viewModel: PetListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Patidost", 
                        style = MaterialTheme.typography.titleLarge,
                        color = PatiGold
                    ) 
                },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Text("👤", style = MaterialTheme.typography.titleLarge)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                uiState.pets.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Henüz dostumuz yok.")
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.pets) { pet ->
                            PetItem(pet = pet, onClick = { onPetClick(pet.id) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pet.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = pet.breed, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

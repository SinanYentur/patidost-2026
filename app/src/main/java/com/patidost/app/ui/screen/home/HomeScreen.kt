package com.patidost.app.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.domain.model.Pet
import com.patidost.app.presentation.pet.HomeUiState
import com.patidost.app.ui.component.core.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPetClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🐾 Patidost") },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Text("👤")
                    }
                }
            )
        }
    ) { paddingValues ->
        SovereignScreenState(
            isLoading = state is HomeUiState.Loading,
            error = (state as? HomeUiState.Error)?.message,
            isEmpty = state is HomeUiState.Empty,
            modifier = Modifier.padding(paddingValues)
        ) {
            if (state is HomeUiState.Content) {
                val pets = (state as HomeUiState.Content).pets
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(pets) { pet ->
                        PetItem(pet = pet, onClick = { onPetClick(pet.id) })
                    }
                }
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: () -> Unit) {
    SovereignCard(onClick = onClick) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pet.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = pet.breed, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

package com.patidost.app.presentation.ui.screen.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patidost.app.domain.repository.PetFilter
import com.patidost.app.presentation.ui.screen.home.FeaturedPet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onNavigateToPetDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val currentFilter by viewModel.filter.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            // Search Bar
            SearchBar(
                query = searchQuery,
                onQueryChanged = viewModel::onQueryChanged
            )

            // Filter Chips
            FilterChips(
                currentFilter = currentFilter,
                onFilterChanged = viewModel::onFilterChanged
            )

            // Content
            Box(modifier = Modifier.fillMaxSize()) {
                when (val state = uiState) {
                    is ExploreUiState.Idle -> {
                        InfoText("Aramaya başlayın veya filtreleri kullanın.")
                    }
                    is ExploreUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is ExploreUiState.Error -> {
                        InfoText(state.message.asString())
                    }
                    is ExploreUiState.Success -> {
                        if (state.isEmpty) {
                            InfoText("Aramanızla eşleşen bir dost bulunamadı.")
                        } else {
                            PetList(pets = state.pets, onPetClicked = onNavigateToPetDetail)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("İsim veya cins ara...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Arama") },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Temizle")
                }
            }
        },
        singleLine = true
    )
}

@Composable
private fun FilterChips(currentFilter: PetFilter, onFilterChanged: (PetFilter) -> Unit) {
    val categories = listOf("Köpek", "Kedi") // Example categories
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) {
            val isSelected = currentFilter.category == it
            FilterChip(
                selected = isSelected,
                onClick = {
                    val newFilter = if (isSelected) currentFilter.copy(category = null) else currentFilter.copy(category = it)
                    onFilterChanged(newFilter)
                },
                label = { Text(it) }
            )
        }
    }
}

@Composable
private fun PetList(pets: List<FeaturedPet>, onPetClicked: (String) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(pets, key = { it.patiId }) {
            ListItem(
                headlineContent = { Text(it.name) },
                supportingContent = { Text(it.breed) },
                modifier = Modifier.clickable { onPetClicked(it.patiId) }
            )
            Divider()
        }
    }
}

@Composable
private fun BoxScope.InfoText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.align(Alignment.Center).padding(16.dp)
    )
}

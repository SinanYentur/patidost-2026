package com.patidost.app.ui.screen.pet.discover

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.theme.PatiGold

/**
 * Discover Components - V2000.70 Hierarchical Migration.
 * RVWL: Atomic search UI components for Discover flow.
 */
@Composable
fun DiscoverContent(
    uiState: Any?, 
    onSearch: (String) -> Unit,
    onPetClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { 
                searchQuery = it
                onSearch(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Patini Bul...", color = Color.White) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = PatiGold) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PatiGold,
                unfocusedBorderColor = Color.White.copy(alpha = 0.3f)
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Text("Keşfetmeye Hazır mısın?", color = PatiGold)
    }
}

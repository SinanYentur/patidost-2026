package com.patidost.app.ui.screen.pet.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.domain.model.Pet

/**
 * PetListContent - V47.80 Rule 97 Import Fix.
 * RVWL: Corrected Box import and Domain Pet sync.
 */
@Composable
fun PetListContent(
    pets: List<Pet>,
    padding: PaddingValues
) {
    if (pets.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Henüz dostumuz yok.")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pets) { pet ->
                Text(text = pet.name) 
            }
        }
    }
}

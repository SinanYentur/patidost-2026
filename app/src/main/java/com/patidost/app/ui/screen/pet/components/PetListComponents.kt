package com.patidost.app.ui.screen.pet.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.patidost.app.domain.model.Pet
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton

/**
 * PetListContent - V10000.70000 Sovereign Implementation.
 * Rule 102: Glass-Z Card integration for premium pet discovery.
 */
@Composable
fun PetListContent(
    pets: List<Pet>,
    padding: PaddingValues
) {
    if (pets.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Henüz dostumuz yok.", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(pets) { pet ->
                PetGlassCard(pet = pet, onDetailClick = { /* Athena Trace: Navigation */ })
            }
        }
    }
}

@Composable
fun PetGlassCard(
    pet: Pet,
    onDetailClick: () -> Unit
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = pet.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = pet.breed,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            PremiumPatiButton(
                text = "İncele",
                onClick = onDetailClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

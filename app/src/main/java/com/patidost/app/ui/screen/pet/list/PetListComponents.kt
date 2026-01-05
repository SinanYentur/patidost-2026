package com.patidost.app.ui.screen.pet.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.patidost.app.ui.behavior.SnapFlingBehavior
import com.patidost.app.ui.component.feature.pet.PetCard
import com.patidost.app.ui.component.core.layout.CustomPullToRefresh
import com.patidost.app.ui.theme.PatiGold

/**
 * PetListComponents - V10000.95000 Sovereign Visuals.
 * Rule 102: Athena Protocol (Glass-Z) with High-Depth Visuals.
 */
@Composable
fun PetListContent(
    uiState: PetListUiState,
    onRefresh: () -> Unit,
    onPetClick: (String) -> Unit
) {
    val snapFlingBehavior = remember { SnapFlingBehavior() }

    CustomPullToRefresh(
        isRefreshing = uiState.isRefreshing,
        onRefresh = onRefresh
    ) {
        if (uiState.pets.isEmpty() && !uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Henüz dostumuz yok.", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1), // 🛡️ Mühür: Feed formatı için tek kolon (Görsel 1 & 4)
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                flingBehavior = snapFlingBehavior
            ) {
                items(
                    items = uiState.pets,
                    key = { it.id },
                    contentType = { "pet_card" }
                ) { pet ->
                    PetCard(
                        pet = pet,
                        onClick = { onPetClick(pet.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun PetStoriesRow() {
    val storyItems = listOf(
        StoryData("leo", "https://images.unsplash.com/photo-1517849845537-4d257902454a"),
        StoryData("zeynep", "https://images.unsplash.com/photo-1494790108377-be9c29b29330"),
        StoryData("burak", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e"),
        StoryData("melisa", "https://images.unsplash.com/photo-1534528741775-53994a69daeb"),
        StoryData("köfte", "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e")
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(storyItems.size) { index ->
            StoryCircle(storyItems[index])
        }
    }
}

@Composable
private fun StoryCircle(data: StoryData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .border(2.dp, PatiGold, CircleShape)
                .padding(3.dp)
        ) {
            AsyncImage(
                model = data.imageUrl,
                contentDescription = data.name,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = data.name,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp
        )
    }
}

data class StoryData(val name: String, val imageUrl: String)

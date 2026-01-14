package com.patidost.app.presentation.ui.screen.explore.composables.swipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.patidost.app.R
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.PetOwner
import com.patidost.app.ui.theme.PatidostTheme

@Composable
fun PetSwipeCard(
    modifier: Modifier = Modifier,
    pet: Pet,
    distance: String,
    onGivePati: () -> Unit,
    onDislike: () -> Unit,
    onLike: () -> Unit,
    onOwnerClick: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box {
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = "${pet.name}'s photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "${pet.name}, ${pet.age} yaşında",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.clickable(onClick = onOwnerClick),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Place, contentDescription = "Distance", tint = Color.White, modifier = Modifier.size(16.dp))
                    Text(
                        text = "~${distance}km",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = onDislike) {
                        Icon(Icons.Default.Close, contentDescription = "Dislike", tint = Color.Red, modifier = Modifier.size(48.dp))
                    }
                    
                    IconButton(onClick = onGivePati) {
                       Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Pati Ver", modifier = Modifier.size(64.dp) ) 
                    }

                    IconButton(onClick = onLike) {
                        Icon(Icons.Default.Favorite, contentDescription = "Like", tint = Color.Green, modifier = Modifier.size(48.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PetSwipeCardPreview() {
    PatidostTheme {
        PetSwipeCard(
            modifier = Modifier.padding(16.dp),
            pet = Pet(
                id = "1",
                name = "Süslü",
                breed = "Golden Retriever",
                imageUrl = "",
                age = 2,
                patiPoints = 120,
                owner = PetOwner(ownerId = "user1", ownerName = "SibelR.", ownerAvatarUrl = "")
            ),
            distance = "5",
            onGivePati = {},
            onDislike = {},
            onLike = {},
            onOwnerClick = {}
        )
    }
}

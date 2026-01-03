package com.patidost.app.ui.screen.pet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.patidost.app.R
import com.patidost.app.domain.model.Pet
import com.patidost.app.ui.component.glassZ

/**
 * Pet Card Component - 2026 Standard.
 * RVWL: Synchronized with com.patidost.app package identity and localized strings.
 */
@Composable
fun PetCard(
    pet: Pet,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 180.dp)
            .glassZ()
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Column {
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .glassZ(blur = 0.dp, shadowElevation = 0.dp),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = pet.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = stringResource(
                    id = R.string.pet_info_format,
                    pet.species,
                    pet.breed
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

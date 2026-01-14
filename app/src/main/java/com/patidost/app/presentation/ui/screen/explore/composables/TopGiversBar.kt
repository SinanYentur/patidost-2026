package com.patidost.app.presentation.ui.screen.explore.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.patidost.app.domain.model.TopGiver
import com.patidost.app.ui.theme.PatidostTheme

@Composable
fun TopGiversBar(topGivers: List<TopGiver>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(topGivers) { giver ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = giver.avatarUrl,
                    contentDescription = giver.name,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = giver.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "❤️ ${giver.patiPointsGiven}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopGiversBarPreview() {
    PatidostTheme {
        val sampleGivers = listOf(
            TopGiver("1", "Arda", "", 16),
            TopGiver("2", "Melis", "", 14),
            TopGiver("3", "Ceyda", "", 17),
            TopGiver("4", "Erkan", "", 12),
            TopGiver("5", "Zeynep", "", 21),
        )
        TopGiversBar(topGivers = sampleGivers)
    }
}

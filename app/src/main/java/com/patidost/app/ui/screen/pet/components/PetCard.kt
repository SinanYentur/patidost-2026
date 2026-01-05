package com.patidost.app.ui.screen.pet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.patidost.app.domain.model.Pet
import com.patidost.app.ui.theme.PatiGold

/**
 * PetCard Sovereign Social Feed Edition - V10000.95000.
 * Rule 102: Athena Protocol (Glass-Z) with High-Depth Visuals.
 * Matches Mockup 1 & 4 (Social Feed Style).
 */
@Composable
fun PetCard(
    pet: Pet,
    onClick: () -> Unit
) {
    Surface(
        color = Color.White.copy(alpha = 0.03f),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column {
            // 🛡️ Header: User Info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = "https://i.pravatar.cc/150?u=${pet.id}",
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = pet.breed, // Mocking owner name with breed for now
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = Color.Gray)
            }

            // 🛡️ Main Image
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = pet.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // 🛡️ Interaction Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    IconButton(onClick = {}) { Icon(Icons.Outlined.FavoriteBorder, null, tint = Color.White) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.ChatBubbleOutline, null, tint = Color.White) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.Send, null, tint = Color.White) }
                }
                IconButton(onClick = {}) { Icon(Icons.Outlined.BookmarkBorder, null, tint = Color.White) }
            }

            // 🛡️ Details & Caption
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    text = "378 beğenme",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                            append("${pet.name.lowercase()} ")
                        }
                        withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.8f))) {
                            append("Bugün parkta çok eğlendi! #köpek #gezi #mutluluk")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    lineHeight = 18.sp
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "6 SAAT ÖNCE",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.4f),
                    fontSize = 10.sp
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

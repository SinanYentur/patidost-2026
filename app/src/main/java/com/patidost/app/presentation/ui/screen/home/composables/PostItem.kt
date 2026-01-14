package com.patidost.app.presentation.ui.screen.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.patidost.app.domain.model.Post

@Composable
fun PostItem(post: Post, onLikeClick: (String) -> Unit) {
    Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column {
            // Author Info
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = post.author.avatarUrl,
                    contentDescription = "Author Avatar",
                    modifier = Modifier.size(40.dp).clip(CircleShape),
                    placeholder = rememberVectorPainter(image = Icons.Default.AccountCircle),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = post.author.name, fontWeight = FontWeight.Bold)
            }

            // Image (if available)
            post.imageUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "Post Image",
                    modifier = Modifier.fillMaxWidth().height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Caption
            Text(text = post.text, modifier = Modifier.padding(16.dp))

            // Interactions
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconToggleButton(checked = false, onCheckedChange = { onLikeClick(post.postId) }) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                }
                Text(text = "${post.likeCount} beğeni")
            }
        }
    }
}

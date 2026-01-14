package com.patidost.app.presentation.ui.screen.explore.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.domain.model.Post

@Composable
fun PostList(posts: List<Post>, onPostClicked: (String) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(posts, key = { it.postId }) {
            ListItem(
                headlineContent = { Text(it.text) },
                supportingContent = { Text("by ${it.author.name}") },
                modifier = Modifier.clickable { onPostClicked(it.postId) }
            )
            Divider()
        }
    }
}

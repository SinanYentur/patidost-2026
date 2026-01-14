package com.patidost.app.presentation.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.patidost.app.domain.model.Post
import com.patidost.app.presentation.navigation.Screen
import com.patidost.app.presentation.ui.component.BottomNavBar
import com.patidost.app.presentation.ui.component.BottomNavItem
import com.patidost.app.presentation.ui.screen.home.composables.PostItem
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val feedItems: LazyPagingItems<Post> = viewModel.feedState.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest(onNavigate)
    }

    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, Screen.Home.route),
        BottomNavItem("Messages", Icons.Default.Mail, Screen.Conversations.route),
        BottomNavItem("Explore", Icons.Default.Explore, Screen.Explore.route),
        BottomNavItem("Friends", Icons.Default.Person, Screen.Friends.route)
    )

    Scaffold(
        bottomBar = {
            BottomNavBar(items = bottomNavItems, currentRoute = Screen.Home.route, onItemClick = onNavigate)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigate(Screen.AddPet.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Yeni Dost Ekle")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(
                count = feedItems.itemCount,
                key = feedItems.itemKey { it.postId }
            ) { index ->
                val post = feedItems[index]
                if (post != null) {
                    PostItem(post = post, onLikeClick = { /* TODO */ })
                }
            }
        }
    }
}

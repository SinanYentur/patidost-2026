package com.patidost.app.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.patidost.app.ui.screen.home.HomeScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onPetClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                val tabs = listOf(
                    Triple("Home", Icons.Default.Home, 0),
                    Triple("Discover", Icons.Default.Search, 1),
                    Triple("Cart", Icons.Default.ShoppingCart, 2),
                    Triple("Profile", Icons.Default.Person, 3)
                )

                tabs.forEach { (label, icon, index) ->
                    NavigationBarItem(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(paddingValues),
            userScrollEnabled = true // 🚀 SWIPE AKTİF (Organik Deneyim)
        ) { page ->
            when (page) {
                0 -> HomeScreen(onPetClick = onPetClick, onProfileClick = onProfileClick)
                1 -> Text("Discover Coming Soon") // Yer tutucu
                2 -> Text("Cart Coming Soon") // Yer tutucu
                3 -> Text("Profile Coming Soon") // Yer tutucu
            }
        }
    }
}
import androidx.compose.ui.unit.dp

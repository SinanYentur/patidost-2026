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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patidost.app.ui.screen.home.HomeScreen
import com.patidost.app.ui.screen.profile.ProfileScreen
import com.patidost.app.ui.screen.pet.discover.DiscoverScreen
import com.patidost.app.ui.screen.cart.main.CartScreen
import kotlinx.coroutines.launch

/**
 * 🛡️ MainScreen - Sovereign Hub V10000.70034.
 * Rule 500: Physical Linkage with Screen physical paths.
 * Rule 420: Fixed hiltViewModel parameter passing.
 */
@Composable
fun MainScreen(
    onPetClick: (String) -> Unit,
    onNavigateToPremium: () -> Unit
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
                    Triple("Akış", Icons.Default.Home, 0),
                    Triple("Keşfet", Icons.Default.Search, 1),
                    Triple("Sepet", Icons.Default.ShoppingCart, 2),
                    Triple("Profil", Icons.Default.Person, 3)
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
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(innerPadding),
            userScrollEnabled = true
        ) { page ->
            when (page) {
                0 -> HomeScreen(
                    onPetClick = onPetClick,
                    onProfileClick = { scope.launch { pagerState.animateScrollToPage(3) } },
                    viewModel = hiltViewModel() // 🛡️ Kablo Bağlandı
                )
                1 -> DiscoverScreen(onPetClick = onPetClick)
                2 -> CartScreen(onCheckoutClick = { /* Navigate to Discover */ })
                3 -> ProfileScreen(onBackClick = { scope.launch { pagerState.animateScrollToPage(0) } })
            }
        }
    }
}

package com.patidost.app.presentation.ui.screen.explore

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patidost.app.R
import com.patidost.app.presentation.navigation.Screen
import com.patidost.app.presentation.ui.component.BottomNavBar
import com.patidost.app.presentation.ui.component.BottomNavItem
import com.patidost.app.presentation.ui.screen.explore.composables.InfoText
import com.patidost.app.presentation.ui.screen.explore.composables.TopGiversBar
import com.patidost.app.presentation.ui.screen.explore.composables.swipe.PetSwipeCard
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()

    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, Screen.Home.route),
        BottomNavItem("Messages", Icons.Default.Mail, Screen.Conversations.route),
        BottomNavItem("Explore", Icons.Default.Explore, Screen.Explore.route),
        BottomNavItem("Friends", Icons.Default.Person, Screen.Friends.route)
    )

    Scaffold(
        bottomBar = {
            BottomNavBar(items = bottomNavItems, currentRoute = Screen.Explore.route, onItemClick = onNavigate)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (uiState.topGivers.isNotEmpty()) {
                TopGiversBar(topGivers = uiState.topGivers)
            }
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else if (uiState.error != null) {
                    InfoText(text = uiState.error!!.asString())
                } else if (uiState.pets.isEmpty()) {
                    InfoText(stringResource(id = R.string.explore_no_pets_found))
                } else {
                    val pets = uiState.pets
                    val offsetX = remember { Animatable(0f) }

                    LaunchedEffect(uiState.pets.size) {
                        offsetX.snapTo(0f)
                    }
                    pets.reversed().forEachIndexed { index, pet ->
                        val isTopCard = index == pets.lastIndex

                        PetSwipeCard(
                            modifier = Modifier
                                .graphicsLayer(
                                    translationX = if (isTopCard) offsetX.value else 0f,
                                    rotationZ = if (isTopCard) offsetX.value / 40f else 0f,
                                    scaleX = 1f - (pets.size - 1 - index) * 0.1f,
                                    scaleY = 1f - (pets.size - 1 - index) * 0.1f
                                )
                                .pointerInput(pet.id) {
                                    if (!isTopCard) return@pointerInput
                                    detectDragGestures(
                                        onDragEnd = {
                                            val target = if (offsetX.value > 300) 1000f else if (offsetX.value < -300) -1000f else 0f
                                            scope.launch {
                                                offsetX.animateTo(target, tween(durationMillis = 300))
                                                if (target != 0f) {
                                                    if (target > 0) viewModel.onEvent(ExploreEvent.SwipeRight) else viewModel.onEvent(ExploreEvent.SwipeLeft)
                                                }
                                            }
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            scope.launch { offsetX.snapTo(offsetX.value + dragAmount.x) }
                                        }
                                    )
                                },
                            pet = pet,
                            distance = "?",
                            onGivePati = { scope.launch { viewModel.onEvent(ExploreEvent.SwipeRight) } },
                            onDislike = { scope.launch { viewModel.onEvent(ExploreEvent.SwipeLeft) } },
                            onLike = { scope.launch { viewModel.onEvent(ExploreEvent.SwipeRight) } },
                            onOwnerClick = { onNavigate(Screen.Profile.createRoute(pet.owner.ownerId)) }
                        )
                    }
                }
            }
        }
    }
}

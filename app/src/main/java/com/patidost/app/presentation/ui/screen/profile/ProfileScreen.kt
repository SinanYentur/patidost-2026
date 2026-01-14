package com.patidost.app.presentation.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.navigationEvent.collectLatest {
            when (it) {
                is ProfileViewModel.NavigationEvent.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (val state = uiState) {
                is ProfileUiState.Loading -> {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                is ProfileUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.message.asString(LocalContext.current),
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                is ProfileUiState.Success -> {
                    ProfileSuccessContent(
                        state = state,
                        onSignOutClicked = viewModel::onSignOutClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileSuccessContent(
    state: ProfileUiState.Success,
    onSignOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = state.userAvatarUrl,
            contentDescription = "Profil Fotoğrafı",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = state.userName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.userEmail, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(32.dp))
        Divider()

        // Menu Items
        ListItem(
            headlineContent = { Text("Favorilerim") },
            leadingContent = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Favorilerim"
                )
            }
        )
        ListItem(
            headlineContent = { Text("İlanlarım") },
            leadingContent = {
                Icon(
                    Icons.AutoMirrored.Filled.ListAlt,
                    contentDescription = "İlanlarım"
                )
            }
        )

        Divider()
        ListItem(
            headlineContent = { Text("Çıkış Yap", color = MaterialTheme.colorScheme.error) },
            leadingContent = {
                Icon(
                    Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Çıkış Yap",
                    tint = MaterialTheme.colorScheme.error
                )
            },
            modifier = Modifier.clickable(onClick = onSignOutClicked)
        )
    }
}

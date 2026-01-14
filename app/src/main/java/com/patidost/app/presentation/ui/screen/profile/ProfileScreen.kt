package com.patidost.app.presentation.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patidost.app.presentation.ui.util.UiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(uiState.user?.name ?: "Profil") }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }

            uiState.error?.let {
                Text(text = it.asString())
            }

            Column {
                uiState.user?.let { user ->
                    Text(text = "Kullanıcı: ${user.name}")
                    Text(text = "E-posta: ${user.email}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                uiState.wallet?.let {
                    Text(text = "Pati Puanı: ${it.patiBalance}")
                }
                Spacer(modifier = Modifier.height(8.dp))
                uiState.subscription?.let {
                    Text(text = "Üyelik: ${it.type} (${it.status})")
                }
            }
        }
    }
}

package com.patidost.app.ui.screen.profile.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.ui.screen.profile.ProfileUiState
import com.patidost.app.ui.screen.profile.ProfileViewModel
import com.patidost.app.ui.theme.PatiGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingsScreen(
    viewModel: ProfileViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profil Ayarları", fontWeight = FontWeight.Bold, color = PatiGold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = PatiGold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when (val state = uiState) {
                is ProfileUiState.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                is ProfileUiState.Success -> {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("İsim: ${state.user.name}", color = Color.White)
                        Text("E-posta: ${state.user.email}", color = Color.White)
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { viewModel.signOut() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                            Text("Çıkış Yap")
                        }
                        Button(onClick = { viewModel.deleteAccount() }, modifier = Modifier.padding(top = 8.dp)) {
                            Text("Hesabı Sil")
                        }
                    }
                }
                is ProfileUiState.Error -> Text(state.message, color = Color.Red)
            }
        }
    }
}

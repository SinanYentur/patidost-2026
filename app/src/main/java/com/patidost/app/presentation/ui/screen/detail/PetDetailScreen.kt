package com.patidost.app.presentation.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patidost.app.presentation.ui.util.UiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    petDetailViewModel: PetDetailViewModel = hiltViewModel(),
    patiPointViewModel: PatiPointViewModel = hiltViewModel()
) {
    val petDetailState by petDetailViewModel.uiState.collectAsState()
    val patiPointState by patiPointViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Handle showing snackbar for pati point actions
    val patiPointError = patiPointState.error
    if (patiPointError != null) {
        val errorMessage = patiPointError.asString() // Resolve string in Composable context
        LaunchedEffect(errorMessage) { // Launch effect when the message is present
            snackbarHostState.showSnackbar(errorMessage)
        }
    }

    LaunchedEffect(patiPointState.success) {
        if (patiPointState.success) {
            snackbarHostState.showSnackbar("Pati Puanı başarıyla verildi!")
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(petDetailState.pet?.name ?: "Detay") }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (petDetailState.isLoading) {
                CircularProgressIndicator()
            }

            petDetailState.error?.let {
                Text(text = it.asString()) // This is a valid Composable context
            }

            petDetailState.pet?.let { pet ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "İsim: ${pet.name}")
                    Text(text = "Tür: ${pet.breed}")
                    Text(text = "Yaş: ${pet.age}")
                    Text(text = "Pati Puanları: ${pet.patiPoints}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            // TODO: Get fromUserId from a real session
                            patiPointViewModel.givePatiPoints(fromUserId = "TODO_USER_ID", toPetId = pet.id, amount = 10)
                        },
                        enabled = !patiPointState.isLoading
                    ) {
                        Text("10 Pati Puanı Ver")
                    }
                }
            }
        }
    }
}

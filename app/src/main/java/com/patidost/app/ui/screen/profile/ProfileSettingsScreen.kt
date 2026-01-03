package com.patidost.app.ui.screen.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.patidost.app.R
import com.patidost.app.ui.component.glassZ

/**
 * Profile Settings Screen - 2026 Android 16 (API 36) PRODUCTION Standard.
 * RVWL: Integrated with Photo Picker (API 36) and corrected Compose API usage.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingsScreen(
    onBackClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.profile_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                modifier = Modifier.glassZ()
            )
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFF1A237E), Color(0xFF121212))))
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            when (val state = uiState) {
                is ProfileUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is ProfileUiState.Success -> ProfileContent(state, viewModel::updateProfile)
                is ProfileUiState.Error -> Text(stringResource(state.messageResId), color = Color.Red)
            }
        }
    }
}

@Composable
private fun ProfileContent(
    state: ProfileUiState.Success,
    onSave: (String, String, String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf(state.user.displayName) }
    var bio by rememberSaveable { mutableStateOf(state.user.bio) }
    var photoUri by rememberSaveable { mutableStateOf(state.user.photoUrl) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { photoUri = it.toString() } }
    )

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .glassZ(blur = 20.dp)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = photoUri.ifEmpty { R.drawable.ic_launcher_background },
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            FloatingActionButton(
                onClick = { photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(20.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text(stringResource(R.string.bio)) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onSave(name, bio, photoUri) },
            enabled = !state.isUpdating,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            if (state.isUpdating) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp), // CORRECTED: size parameter removed, used Modifier.size
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(stringResource(R.string.save))
            }
        }
        
        state.messageResId?.let {
            Text(stringResource(it), color = Color.Green, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

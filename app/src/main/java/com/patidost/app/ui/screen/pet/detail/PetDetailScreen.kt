package com.patidost.app.ui.screen.pet.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.*
import com.patidost.app.util.SovereignHapticFeedback

/**
 * PetDetailScreen - V10001.00000 Snowy A+++ Edition.
 * REPLICA: Synchronized with the premium snowy/light glass UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel,
    onBackClick: () -> Unit,
    onAdoptSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val hapticFeedback = LocalHapticFeedback.current
    
    val sovereignHaptic = SovereignHapticFeedback(context, hapticFeedback)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dostumuz", color = TextDark, fontWeight = FontWeight.Black) },
                navigationIcon = {
                    IconButton(onClick = {
                        sovereignHaptic.perform(SovereignHapticFeedback.FeedbackType.LIGHT)
                        onBackClick()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextDark)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(calculateSnowyGradient())
                .padding(padding)
        ) {
            when (val state = uiState) {
                is PetDetailUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(androidx.compose.ui.Alignment.Center), 
                        color = PatiGold
                    )
                }
                is PetDetailUiState.Success -> {
                    val pet = state.pet
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        GlassCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(320.dp)
                        ) {
                            AsyncImage(
                                model = pet.imageUrl,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = pet.name,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Black,
                            color = TextDark
                        )

                        Text(
                            text = "${pet.species} • ${pet.breed} • ${pet.age} Yaş",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextGray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        GlassCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Hikayesi", color = PatiGold, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = pet.description, 
                                    color = TextDark,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        PremiumPatiButton(
                            text = "Hemen Sahiplen",
                            onClick = { 
                                sovereignHaptic.perform(SovereignHapticFeedback.FeedbackType.HEAVY)
                                viewModel.onAdoptPet(pet.id) 
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                is PetDetailUiState.Error -> {
                    Text(
                        text = state.message, 
                        color = Color.Red, 
                        modifier = Modifier.align(androidx.compose.ui.Alignment.Center)
                    )
                }
            }
        }
    }
}

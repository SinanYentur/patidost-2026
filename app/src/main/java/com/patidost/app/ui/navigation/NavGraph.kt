package com.patidost.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patidost.app.ui.screen.auth.AuthScreen
import com.patidost.app.ui.screen.auth.AuthUiState
import com.patidost.app.ui.screen.auth.AuthViewModel
import com.patidost.app.ui.screen.pet.PetDetailScreen
import com.patidost.app.ui.screen.pet.PetDetailViewModel
import com.patidost.app.ui.screen.pet.PetListScreen
import com.patidost.app.ui.screen.pet.PetListViewModel
import com.patidost.app.ui.screen.profile.ProfileSettingsScreen
import com.patidost.app.ui.screen.profile.ProfileViewModel

/**
 * Main Navigation Graph - 2026 Standard.
 * RVWL: Unified routing for Auth, Pets, and Profile Management.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = "auth"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("auth") {
            val viewModel: AuthViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(uiState) {
                if (uiState is AuthUiState.Authenticated) {
                    navController.navigate("pet_list") {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            }

            AuthScreen(
                state = uiState,
                onAuthAction = viewModel::onAuthAction,
                onToggleMode = viewModel::toggleMode
            )
        }

        composable("pet_list") {
            val viewModel: PetListViewModel = hiltViewModel()
            PetListScreen(
                viewModel = viewModel,
                onPetClick = { petId ->
                    navController.navigate("pet_detail/$petId")
                },
                onProfileClick = {
                    navController.navigate("profile")
                }
            )
        }

        composable("pet_detail/{petId}") {
            val viewModel: PetDetailViewModel = hiltViewModel()
            PetDetailScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("profile") {
            val viewModel: ProfileViewModel = hiltViewModel()
            ProfileSettingsScreen(
                onBackClick = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}

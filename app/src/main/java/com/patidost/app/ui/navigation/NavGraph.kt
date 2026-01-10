package com.patidost.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.patidost.app.ui.screen.auth.AuthScreen
import com.patidost.app.ui.screen.auth.AuthViewModel
import com.patidost.app.ui.screen.main.MainScreen
import com.patidost.app.ui.screen.profile.ProfileSettingsScreen
import com.patidost.app.ui.screen.profile.ProfileViewModel
import com.patidost.app.ui.screen.premium.PremiumScreen
import com.patidost.app.ui.screen.premium.PremiumViewModel
import com.patidost.app.ui.screen.pet.PetDetailScreen
import com.patidost.app.ui.screen.pet.PetDetailViewModel

/**
 * 🛡️ NavGraph - Sovereign Navigation Hub V10000.70111.
 * Rule 310: Physical sync with pet package (removed .detail sub-package).
 * ARTICLE 15: Navigation DNA aligned with V10000.70062 Screen location.
 */
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        // 🔐 Auth Gate
        composable("auth") {
            AuthScreen(
                viewModel = hiltViewModel<AuthViewModel>(),
                onAuthSuccess = { 
                    navController.navigate("main") {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }

        // 🐾 Main Core
        composable("main") {
            MainScreen(
                onPetClick = { petId -> 
                    navController.navigate("pet_detail/$petId") 
                },
                onNavigateToPremium = { 
                    navController.navigate("premium") 
                }
            )
        }

        // 👤 Profile Hub
        composable("profile") {
            ProfileSettingsScreen(
                viewModel = hiltViewModel<ProfileViewModel>(),
                onBackClick = { navController.popBackStack() }
            )
        }

        // 💎 Premium Gate
        composable("premium") {
            PremiumScreen(
                viewModel = hiltViewModel<PremiumViewModel>(),
                onBackClick = { navController.popBackStack() }
            )
        }

        // 📄 Pet Detail: Physical Stream (V10000.70111 Reseal)
        composable(
            route = "pet_detail/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.StringType })
        ) {
            PetDetailScreen(
                viewModel = hiltViewModel<PetDetailViewModel>(),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

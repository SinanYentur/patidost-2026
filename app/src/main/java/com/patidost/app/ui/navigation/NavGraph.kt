package com.patidost.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.patidost.app.ui.screen.auth.login.LoginScreen
import com.patidost.app.ui.screen.main.MainScreen
import com.patidost.app.ui.screen.profile.ProfileSettingsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // 🔐 Auth: Giriş Kapısı
        composable("login") {
            LoginScreen(
                onAuthSuccess = { 
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // 🐾 Main: Organik İskelet (Home + Swipeable Tabs)
        composable("main") {
            MainScreen(
                onPetClick = { petId -> 
                    navController.navigate("pet_detail/$petId") 
                },
                onProfileClick = { 
                    navController.navigate("profile") 
                }
            )
        }

        // 👤 Profile: Ayarlar
        composable("profile") {
            ProfileSettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // 📄 Pet Detail: Detaylar
        composable(
            route = "pet_detail/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.StringType })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: ""
            // PetDetailScreen fiziksel mühürleme aşamasında eklenecek
        }
    }
}

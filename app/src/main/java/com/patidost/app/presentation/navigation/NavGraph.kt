package com.patidost.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.patidost.app.presentation.ui.screen.add_pet.AddPetScreen
import com.patidost.app.presentation.ui.screen.auth.AuthScreen
import com.patidost.app.presentation.ui.screen.chat.ChatScreen
import com.patidost.app.presentation.ui.screen.conversations.ConversationsScreen
import com.patidost.app.presentation.ui.screen.detail.PetDetailScreen
import com.patidost.app.presentation.ui.screen.explore.ExploreScreen
import com.patidost.app.presentation.ui.screen.friends.FriendsScreen
import com.patidost.app.presentation.ui.screen.home.HomeScreen
import com.patidost.app.presentation.ui.screen.profile.ProfileScreen
import com.patidost.app.presentation.ui.screen.purchase.GoldPlansScreen

@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Auth.route) {
            AuthScreen(
                onNavigateToHome = { 
                    navController.navigate(Screen.Explore.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(Screen.Conversations.route) {
            ConversationsScreen(
                onNavigateToChat = { conversationId -> 
                    navController.navigate(Screen.Chat.createRoute(conversationId))
                }
            )
        }
        composable(Screen.Friends.route) {
            FriendsScreen(
                onNavigateToProfile = { userId ->
                    navController.navigate(Screen.Profile.createRoute(userId))
                }
            )
        }
        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument("conversationId") { type = NavType.StringType })
        ) {
            ChatScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = Screen.PetDetail.route,
            arguments = listOf(navArgument("petId") { type = NavType.StringType })
        ) {
            PetDetailScreen()
        }
        composable(Screen.Explore.route) {
            ExploreScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(
            route = Screen.Profile.route,
            arguments = listOf(navArgument(Screen.Profile.ARG_USER_ID) { type = NavType.StringType; nullable = true })
        ) {
            ProfileScreen()
        }
        composable(Screen.AddPet.route) {
            AddPetScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Screen.GoldPlans.route) {
            GoldPlansScreen()
        }
    }
}

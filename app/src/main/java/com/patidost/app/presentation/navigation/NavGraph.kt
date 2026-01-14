package com.patidost.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patidost.app.presentation.ui.screen.auth.LoginScreen
import com.patidost.app.presentation.ui.screen.home.HomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen()
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}

// Placeholder screens to make the NavGraph buildable
@Composableun LoginScreen() { /* TODO in later KAPIs */ }

@Composable
fun HomeScreen() { /* TODO in later KAPIs */ }

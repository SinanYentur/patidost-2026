package com.patidost.app.presentation.navigation

/**
 * Defines all possible screens in the application for type-safe navigation.
 */
sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
}

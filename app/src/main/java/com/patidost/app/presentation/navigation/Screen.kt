package com.patidost.app.presentation.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Friends : Screen("friends")
    object Conversations : Screen("conversations")
    object Chat : Screen("chat/{conversationId}") {
        fun createRoute(conversationId: String) = "chat/$conversationId"
    }
    object Profile : Screen("profile?userId={userId}") {
        const val ARG_USER_ID = "userId"
        fun createRoute(userId: String) = "profile?userId=$userId"
    }
    object AddPet : Screen("add_pet")
    object GoldPlans : Screen("gold_plans")
    object PetDetail : Screen("pet_detail/{petId}") {
        fun createRoute(petId: String) = "pet_detail/$petId"
    }
}

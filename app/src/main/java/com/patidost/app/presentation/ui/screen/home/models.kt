package com.patidost.app.presentation.ui.screen.home

/**
 * Represents a user in the top givers list.
 * This model is UI-specific and can be different from the domain User model.
 */
data class TopGiver(
    val name: String,
    val profileImageUrl: String,
    val score: Int
)

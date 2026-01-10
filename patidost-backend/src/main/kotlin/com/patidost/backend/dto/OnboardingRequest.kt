package com.patidost.backend.dto

import kotlinx.serialization.Serializable

/**
 * 🛡️ OnboardingRequest - Data capsule for the user onboarding process.
 */
@Serializable
data class OnboardingRequest(
    val name: String,
    val birthDate: String,
    val hasPet: Boolean
)

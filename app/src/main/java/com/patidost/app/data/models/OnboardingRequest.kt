package com.patidost.app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingRequest(
    val name: String,
    val birthDate: String,
    val hasPet: Boolean
)

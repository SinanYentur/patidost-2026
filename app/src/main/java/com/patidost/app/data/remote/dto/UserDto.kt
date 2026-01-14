package com.patidost.app.data.remote.dto

import com.google.gson.annotations.SerializedName


data class UserDto(
    @SerializedName("id") val id: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("verification_level") val verificationLevel: Int = 0,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("pati_points") val patiPoints: Int? = 0 // The single, authoritative source for points
)

package com.patidost.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.patidost.app.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val name: String,
    val profileImageUrl: String = "",
    val isPremium: Boolean = false,
    val trustScore: Int = 0,
    val successfulAdoptions: Int = 0,
    val reportsCount: Int = 0
) {
    fun toDomain() = User(
        id = id,
        email = email,
        name = name,
        profileImageUrl = profileImageUrl,
        isPremium = isPremium,
        trustScore = trustScore,
        successfulAdoptions = successfulAdoptions,
        reportsCount = reportsCount
    )

    companion object {
        fun fromDomain(user: User) = UserEntity(
            id = user.id,
            email = user.email,
            name = user.name,
            profileImageUrl = user.profileImageUrl,
            isPremium = user.isPremium,
            trustScore = user.trustScore,
            successfulAdoptions = user.successfulAdoptions,
            reportsCount = user.reportsCount
        )
    }
}

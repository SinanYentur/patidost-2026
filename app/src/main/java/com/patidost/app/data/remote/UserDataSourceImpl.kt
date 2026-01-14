package com.patidost.app.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.remote.dto.UserDto
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserDataSource {

    override suspend fun createUser(userDto: UserDto): Resource<Unit> {
        return try {
            firestore.collection("users").document(userDto.id).set(userDto).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred while creating user profile."))
        }
    }

    override suspend fun getUser(userId: String): Resource<UserDto?> {
        return try {
            val document = firestore.collection("users").document(userId).get().await()
            val user = document.toObject(UserDto::class.java)
            if (user != null) {
                Resource.Success(user)
            } else {
                Resource.Error(UiText.DynamicString("User not found."))
            }
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred while fetching user data."))
        }
    }

    override suspend fun updateUser(userDto: UserDto): Resource<Unit> {
        return try {
            firestore.collection("users").document(userDto.id).set(userDto).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred while updating user data."))
        }
    }
}

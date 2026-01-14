package com.patidost.app.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.remote.dto.UserDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementation of UserDataSource using Cloud Firestore.
 * @param firestore The FirebaseFirestore instance provided by Hilt.
 */
class UserDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserDataSource {

    private val usersCollection = firestore.collection("users")

    override suspend fun createUser(userDto: UserDto): Resource<Unit> {
        return try {
            usersCollection.document(userDto.id).set(userDto).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred while creating user.")
        }
    }

    override suspend fun getUser(userId: String): Resource<UserDto?> {
        return try {
            val document = usersCollection.document(userId).get().await()
            val userDto = document.toObject(UserDto::class.java)
            Resource.Success(userDto)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred while fetching user.")
        }
    }
}

package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.FriendRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeFriendRepository @Inject constructor() : FriendRepository {

    private val friends = listOf(
        User(uid = "2", name = "Arda", email = "", avatarUrl = "", patiPoints = 10, status = "active", verificationLevel = 2),
        User(uid = "3", name = "Ceyda", email = "", avatarUrl = "", patiPoints = 17, status = "active", verificationLevel = 2)
    )

    override fun getFriends(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        // Simulate network delay
        kotlinx.coroutines.delay(500)
        emit(Resource.Success(friends))
    }

    override suspend fun sendFriendRequest(userId: String): Resource<Unit> {
        println("FakeFriendRepository: Friend request sent to $userId")
        return Resource.Success(Unit)
    }

    override suspend fun acceptFriendRequest(userId: String): Resource<Unit> {
        println("FakeFriendRepository: Friend request from $userId accepted.")
        return Resource.Success(Unit)
    }

    override suspend fun rejectFriendRequest(userId: String): Resource<Unit> {
        println("FakeFriendRepository: Friend request from $userId rejected.")
        return Resource.Success(Unit)
    }

    override suspend fun removeFriend(userId: String): Resource<Unit> {
        println("FakeFriendRepository: Removed friend $userId")
        return Resource.Success(Unit)
    }
}

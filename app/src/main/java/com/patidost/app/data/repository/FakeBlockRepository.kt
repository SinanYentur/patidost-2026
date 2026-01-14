package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.BlockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeBlockRepository @Inject constructor() : BlockRepository {

    private val blockedUsers = mutableListOf<User>()

    override fun getBlockedUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        kotlinx.coroutines.delay(300)
        emit(Resource.Success(blockedUsers.toList()))
    }

    override suspend fun blockUser(userId: String): Resource<Unit> {
        // In a real app, we would get user details first
        blockedUsers.add(User(uid = userId, name = "Blocked User $userId", email = "", avatarUrl = "", patiPoints = 0, status = "active", verificationLevel = 1))
        println("FakeBlockRepository: User $userId blocked.")
        return Resource.Success(Unit)
    }

    override suspend fun unblockUser(userId: String): Resource<Unit> {
        blockedUsers.removeAll { it.uid == userId }
        println("FakeBlockRepository: User $userId unblocked.")
        return Resource.Success(Unit)
    }
}

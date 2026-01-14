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
        // In a real app, this list would be populated from a remote or local data source
        // For now, it just returns the in-memory list.
        emit(Resource.Success(blockedUsers.toList()))
    }

    override suspend fun blockUser(userId: String): Resource<Unit> {
        // In a real app, you might want to confirm the user exists before blocking.
        // Here, we just add a placeholder user to the list.
        if (blockedUsers.none { it.uid == userId }) {
            val userToBlock = User(uid = userId, name = "Blocked User $userId", email = "", avatarUrl = "", patiPoints = 0, status = "active", verificationLevel = 1)
            blockedUsers.add(userToBlock)
        }
        println("FakeBlockRepository: User $userId blocked.")
        return Resource.Success(Unit)
    }

    override suspend fun unblockUser(userId: String): Resource<Unit> {
        blockedUsers.removeAll { it.uid == userId }
        println("FakeBlockRepository: User $userId unblocked.")
        return Resource.Success(Unit)
    }
}

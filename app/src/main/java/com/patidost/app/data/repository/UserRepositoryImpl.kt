package com.patidost.app.data.repository

import androidx.room.withTransaction
import com.patidost.app.core.util.Resource
import com.patidost.app.data.local.PatiDostDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.UserDao
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val db: PatiDostDatabase // Inject the whole DB to use its transaction mechanism
) : UserRepository {

    // Access DAOs through the database instance
    private val userDao: UserDao = db.userDao()
    private val petDao: PetDao = db.petDao()

    override suspend fun givePatiPoints(fromUserId: String, toPetId: String, amount: Int): Resource<Unit> {
        return try {
            db.withTransaction {
                // 1. Check user balance
                val currentUser = userDao.getUserById(fromUserId) 
                    ?: throw IllegalStateException("User not found.")

                if (currentUser.patiPoints < amount) {
                    throw IllegalStateException("Insufficient pati points.")
                }

                // 2. Decrease user's balance
                userDao.updatePatiPoints(fromUserId, -amount)

                // 3. Increase pet's balance
                petDao.addPatiPoints(toPetId, amount)
            }
            Resource.Success(Unit)
        } catch (e: IllegalStateException) {
            Resource.Error(UiText.DynamicString(e.message ?: "Transaction failed"))
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString("An unexpected error occurred during the transaction."))
        }
    }
}

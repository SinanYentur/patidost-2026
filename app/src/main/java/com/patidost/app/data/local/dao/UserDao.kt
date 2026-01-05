package com.patidost.app.data.local.dao

import androidx.room.*
import com.patidost.app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * User DAO - V10000.18400 Sovereign Identity.
 * Rule 106: SSOT Enforcement for User Profile and Settings.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: String): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: String)

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()
}

package com.patidost.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.patidost.app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the users table.
 */
@Dao
interface UserDao {

    /**
     * Inserts or updates a user. If the user already exists, it's replaced.
     * @param user The user to be inserted or updated.
     */
    @Upsert
    suspend fun upsertUser(user: UserEntity)

    /**
     * Retrieves a user by their unique ID.
     * @param uid The unique ID of the user.
     * @return A flow of the user, which will emit a new value if the user data changes.
     */
    @Query("SELECT * FROM users WHERE uid = :uid LIMIT 1")
    fun getUser(uid: String): Flow<UserEntity?>
}

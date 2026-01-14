package com.patidost.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.patidost.app.data.local.entity.PetEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the pets table.
 */
@Dao
interface PetDao {

    @Upsert
    suspend fun upsertAll(pets: List<PetEntity>)

    @Query("SELECT * FROM pets ORDER BY name ASC")
    fun getAll(): Flow<List<PetEntity>>

    @Query("DELETE FROM posts")
    suspend fun clearAll()

    @Query("UPDATE pets SET patiPoints = patiPoints + :points WHERE id = :petId")
    suspend fun addPatiPoints(petId: String, points: Int)

    @Query("SELECT * FROM pets WHERE id = :petId")
    suspend fun getPetById(petId: String): PetEntity?
}

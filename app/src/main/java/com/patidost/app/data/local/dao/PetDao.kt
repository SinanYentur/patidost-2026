package com.patidost.app.data.local.dao

import androidx.room.*
import com.patidost.app.data.model.PetEntity
import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ PetDao - V10000.70013 Sovereign Persistence Layer.
 * Rule 300.2: Schema Safety & SSOT.
 */
@Dao
interface PetDao {
    @Query("SELECT * FROM pets ORDER BY createdAt DESC")
    fun getAllPets(): Flow<List<PetEntity>>

    @Query("SELECT * FROM pets WHERE id = :id")
    suspend fun getPetById(id: String): PetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Delete
    suspend fun deletePet(pet: PetEntity)

    @Query("DELETE FROM pets")
    suspend fun deleteAllPets()
}

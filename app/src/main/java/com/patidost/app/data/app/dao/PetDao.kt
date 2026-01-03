package com.patidost.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.patidost.app.data.local.entity.PetEntity
import kotlinx.coroutines.flow.Flow

/**
 * Pet Data Access Object.
 * RVWL: Reactive Room queries (Flow).
 */
@Dao
interface PetDao {

    @Query("SELECT * FROM pets WHERE ownerId = :ownerId ORDER BY createdAt DESC")
    fun getPetsByOwner(ownerId: String): Flow<List<PetEntity>>

    @Query("SELECT * FROM pets WHERE id = :petId")
    fun getPetById(petId: String): Flow<PetEntity?>

    @Upsert
    suspend fun upsertPet(pet: PetEntity)

    @Query("DELETE FROM pets WHERE id = :petId")
    suspend fun deletePet(petId: String)

    @Query("DELETE FROM pets WHERE ownerId = :ownerId")
    suspend fun deletePetsByOwner(ownerId: String)
}

package com.patidost.app.data.local

import androidx.room.*
import com.patidost.app.data.model.PetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pets ORDER BY createdAt DESC")
    fun getAllPets(): Flow<List<PetEntity>>

    @Query("SELECT * FROM pets WHERE id = :id")
    fun getPetById(id: String): Flow<PetEntity?>

    @Upsert
    suspend fun upsertPets(pets: List<PetEntity>)

    @Query("DELETE FROM pets WHERE id = :petId")
    suspend fun deletePet(petId: String)
}

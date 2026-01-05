package com.patidost.app.data.local.dao

import androidx.room.*
import com.patidost.app.data.local.entity.PetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pets")
    fun getAllPets(): Flow<List<PetEntity>>

    @Query("SELECT * FROM pets WHERE id = :petId")
    fun getPetById(petId: String): Flow<PetEntity?>

    @Query("SELECT * FROM pets WHERE id = :petId")
    suspend fun getPetByIdDirect(petId: String): PetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPets(pets: List<PetEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Query("DELETE FROM pets WHERE id = :petId")
    suspend fun deletePetById(petId: String)

    @Query("SELECT * FROM pets WHERE ownerId = :ownerId")
    fun getPetsByOwner(ownerId: String): Flow<List<PetEntity>>

    @Query("DELETE FROM pets")
    suspend fun clearPets()
}

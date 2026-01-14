package com.patidost.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.patidost.app.data.local.entity.PetEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the pets table.
 * Defines the database interactions for PetEntity.
 */
@Dao
interface PetDao {

    /**
     * Inserts or updates pets in the database. If a pet already exists, it's replaced.
     * @param pets The list of pets to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAll(pets: List<PetEntity>)

    /**
     * Retrieves all pets from the database, ordered by name.
     * The returned Flow will automatically emit new values whenever the data changes.
     * @return A flow of the list of all pets.
     */
    @Query("SELECT * FROM pets ORDER BY name ASC")
    fun getAll(): Flow<List<PetEntity>>
    
    /**
     * Deletes all pets from the table. 
     * This is useful for clearing the cache before a new fetch.
     */
    @Query("DELETE FROM pets")
    suspend fun clearAll()
}

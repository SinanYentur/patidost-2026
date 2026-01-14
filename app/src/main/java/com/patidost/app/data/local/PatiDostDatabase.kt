package com.patidost.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.entity.PetEntity

/**
 * The main database class for the application.
 * This class is annotated with @Database and lists all the entities and the database version.
 */
@Database(
    entities = [PetEntity::class],
    version = 1,
    exportSchema = false // Schema export is disabled for this project stage
)
abstract class PatiDostDatabase : RoomDatabase() {

    /**
     * Abstract method to get the Data Access Object for Pet operations.
     * Room will generate the implementation for this method.
     */
    abstract fun petDao(): PetDao
}

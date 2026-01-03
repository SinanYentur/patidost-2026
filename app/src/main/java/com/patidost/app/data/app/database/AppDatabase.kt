package com.patidost.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.entity.PetEntity

/**
 * Main Application Database.
 * RVWL: Android Room guidelines (V1 Initial Schema).
 */
@Database(
    entities = [PetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}

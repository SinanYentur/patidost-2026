package com.patidost.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.UserDao
import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.data.local.entity.UserEntity

/**
 * PatiDatabase - V49.00 Rule 97 Final Seal.
 * RVWL: Unified SSOT with Pet and User entities.
 */
@Database(
    entities = [PetEntity::class, UserEntity::class],
    version = 2, // Rule 92: Incremented for UserEntity integration
    exportSchema = false
)
abstract class PatiDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun userDao(): UserDao
}

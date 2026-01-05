package com.patidost.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.UserDao
import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.data.local.entity.UserEntity

@Database(
    entities = [PetEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false // Rule 106: Schema export disabled to clear KSP warnings.
)
abstract class PatidostDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "patidost_sovereign.db"
    }
}

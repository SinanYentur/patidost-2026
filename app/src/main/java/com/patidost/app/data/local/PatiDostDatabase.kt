package com.patidost.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.patidost.app.data.local.dao.CommentDao
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.PostDao
import com.patidost.app.data.local.dao.UserDao
import com.patidost.app.data.local.entity.CommentEntity
import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.data.local.entity.PostEntity
import com.patidost.app.data.local.entity.UserEntity

@Database(
    entities = [PetEntity::class, UserEntity::class, PostEntity::class, CommentEntity::class],
    version = 2, // Version incremented to 2 to handle new columns
    exportSchema = true // Exporting schema is now mandatory
)
abstract class PatiDostDatabase : RoomDatabase() {

    abstract fun petDao(): PetDao
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        /**
         * Migration from version 1 to 2. Adds patiPoints columns with a default value.
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Add patiPoints to users table
                db.execSQL("ALTER TABLE users ADD COLUMN patiPoints INTEGER NOT NULL DEFAULT 0")
                // Add patiPoints to pets table
                db.execSQL("ALTER TABLE pets ADD COLUMN patiPoints INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}

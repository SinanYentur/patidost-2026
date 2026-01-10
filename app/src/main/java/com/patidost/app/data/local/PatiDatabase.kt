package com.patidost.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.UserDao
import com.patidost.app.data.model.PetEntity
import com.patidost.app.data.local.entity.UserEntity

/**
 * 🛡️ PatiDatabase - V10000.70013 Sovereign Persistence Hub.
 * Rule 300.2: Schema Safety & SSOT.
 * Note: Unified with Global Pet Model and Legacy User Entity.
 */
@Database(
    entities = [PetEntity::class, UserEntity::class],
    version = 3, // Rule 420: Incremented for Global Pet Model Sync
    exportSchema = true // 🛡️ RULE 300.2: DNA SAPMASI DÜZELTİLDİ. Şema aktarımı zorunludur.
)
abstract class PatiDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun userDao(): UserDao
}

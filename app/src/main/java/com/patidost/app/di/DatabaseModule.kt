package com.patidost.app.di

import android.content.Context
import androidx.room.Room
import com.patidost.app.data.local.PatiDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database Module - V48.40 Rule 97 DI Purity.
 * RVWL: Synchronized DAO bindings with correct physical packages.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PatiDatabase {
        return Room.databaseBuilder(
            context,
            PatiDatabase::class.java,
            "patidost_db"
        ).build()
    }

    @Provides
    fun providePetDao(db: PatiDatabase): PetDao = db.petDao()

    @Provides
    fun provideUserDao(db: PatiDatabase): UserDao = db.userDao()
}

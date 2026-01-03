package com.patidost.app.data.di

import android.content.Context
import androidx.room.Room
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database Module for Room Dependencies.
 * RVWL: Android Hilt guidelines (Singleton Database Provider).
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "patidost_db"
    ).build()

    @Provides
    fun providePetDao(database: AppDatabase): PetDao = database.petDao()
}

package com.patidost.app.di

import android.content.Context
import androidx.room.Room
import com.patidost.app.data.local.PatiDostDatabase
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of the PatiDostDatabase.
     */
    @Provides
    @Singleton
    fun providePatiDostDatabase(@ApplicationContext context: Context): PatiDostDatabase {
        return Room.databaseBuilder(
            context,
            PatiDostDatabase::class.java,
            "patidost.db"
        ).build()
    }

    /**
     * Provides the PetDao.
     */
    @Provides
    fun providePetDao(database: PatiDostDatabase): PetDao = database.petDao()

    /**
     * Provides the UserDao.
     */
    @Provides
    fun provideUserDao(database: PatiDostDatabase): UserDao = database.userDao()
}

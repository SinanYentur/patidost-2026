package com.patidost.app.di

import android.content.Context
import androidx.room.Room
import com.patidost.app.data.local.PatiDostDatabase
import com.patidost.app.data.local.dao.CommentDao
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.dao.PostDao
import com.patidost.app.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePatiDostDatabase(@ApplicationContext context: Context): PatiDostDatabase {
        return Room.databaseBuilder(
            context,
            PatiDostDatabase::class.java,
            "patidost.db"
        )
        .addMigrations(PatiDostDatabase.MIGRATION_1_2)
        .build()
    }

    @Provides
    fun providePetDao(database: PatiDostDatabase): PetDao = database.petDao()

    @Provides
    fun provideUserDao(database: PatiDostDatabase): UserDao = database.userDao()

    @Provides
    fun providePostDao(database: PatiDostDatabase): PostDao = database.postDao()

    @Provides
    fun provideCommentDao(database: PatiDostDatabase): CommentDao = database.commentDao()
}

package com.patidost.app.di

import com.patidost.app.data.remote.AuthRemoteDataSource
import com.patidost.app.data.repository.UserRepositoryImpl
import com.patidost.app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideUserRepository(remoteDataSource: AuthRemoteDataSource): UserRepository =
        UserRepositoryImpl(remoteDataSource)
}

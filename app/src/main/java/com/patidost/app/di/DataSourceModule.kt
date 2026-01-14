package com.patidost.app.di

import com.patidost.app.data.remote.AuthDataSource
import com.patidost.app.data.remote.AuthDataSourceImpl
import com.patidost.app.data.remote.HomeDataSource
import com.patidost.app.data.remote.HomeDataSourceImpl
import com.patidost.app.data.remote.UserDataSource
import com.patidost.app.data.remote.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource = authDataSourceImpl

    @Provides
    @Singleton
    fun provideUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource = userDataSourceImpl

    @Provides
    @Singleton
    fun provideHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource = homeDataSourceImpl
}

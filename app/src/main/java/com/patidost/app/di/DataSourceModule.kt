package com.patidost.app.di

import com.patidost.app.data.remote.AuthDataSource
import com.patidost.app.data.remote.AuthDataSourceImpl
import com.patidost.app.data.remote.UserDataSource
import com.patidost.app.data.remote.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource
}

package com.patidost.app.data.di

import com.patidost.app.data.repository.PetRepositoryImpl
import com.patidost.app.data.repository.UserRepositoryImpl
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Single Source of Truth for Repository Bindings.
 * RVWL: Synchronized to prevent overlapping Auth/User repositories.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPetRepository(
        petRepositoryImpl: PetRepositoryImpl
    ): PetRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}

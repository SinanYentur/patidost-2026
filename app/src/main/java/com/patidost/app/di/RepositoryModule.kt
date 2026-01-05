package com.patidost.app.di

import com.patidost.app.data.repository.AuthRepositoryImpl
import com.patidost.app.data.repository.PetRepositoryImpl
import com.patidost.app.data.repository.UserRepositoryImpl
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository Module - V48.50 Rule 97 DI Purity.
 * RVWL: Unified interface bindings for Auth, Pet, and User.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

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

package com.patidost.app.di

import com.patidost.app.data.repository.AuthRepositoryImpl
import com.patidost.app.data.repository.ConversationRepositoryImpl
import com.patidost.app.data.repository.EconomyRepositoryImpl
import com.patidost.app.data.repository.HomeRepositoryImpl
import com.patidost.app.data.repository.PetRepositoryImpl
import com.patidost.app.data.repository.PostRepositoryImpl
import com.patidost.app.data.repository.UserRepositoryImpl
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.ConversationRepository
import com.patidost.app.domain.repository.EconomyRepository
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.repository.PostRepository
import com.patidost.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindConversationRepository(conversationRepositoryImpl: ConversationRepositoryImpl): ConversationRepository

    @Binds
    @Singleton
    abstract fun bindEconomyRepository(economyRepositoryImpl: EconomyRepositoryImpl): EconomyRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindPetRepository(petRepositoryImpl: PetRepositoryImpl): PetRepository

    @Binds
    @Singleton
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}

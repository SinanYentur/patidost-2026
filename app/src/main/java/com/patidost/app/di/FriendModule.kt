package com.patidost.app.di

import com.patidost.app.data.repository.FakeFriendRepository
import com.patidost.app.domain.repository.FriendRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FriendModule {

    @Provides
    @Singleton
    fun provideFriendRepository(): FriendRepository {
        // Development phase: Provide the fake repository.
        // In production, this will be replaced with the real implementation.
        return FakeFriendRepository()
    }
}

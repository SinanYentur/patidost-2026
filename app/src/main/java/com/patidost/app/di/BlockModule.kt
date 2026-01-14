package com.patidost.app.di

import com.patidost.app.data.repository.FakeBlockRepository
import com.patidost.app.domain.repository.BlockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BlockModule {

    @Provides
    @Singleton
    fun provideBlockRepository(): BlockRepository {
        // Development phase: Provide the fake repository.
        return FakeBlockRepository()
    }
}

package com.patidost.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * 🛡️ AppModule - Sovereign Core DI Hub V10000.70059.
 * Rule 420: Refactored to remove duplicate UserRepository binding.
 * ARTICLE 9: IO Dispatcher and external dependencies hub.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

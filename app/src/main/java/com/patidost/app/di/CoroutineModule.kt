package com.patidost.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Singleton

/**
 * 🛡️ FAZ 3: COROUTINE HARDENING MÜHRÜ
 * Rule 190: Exception isolation for background services.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(
        SupervisorJob() + 
        Dispatchers.Default + 
        CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable, "🚨 CRITICAL: ApplicationScope Leak/Crash detected")
        }
    )
}

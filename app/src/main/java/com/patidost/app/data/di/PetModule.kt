package com.patidost.app.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Pet Module - DEPRECATED (Moved to RepositoryModule).
 * RVWL: Unified repository bindings to prevent [Dagger/DuplicateBindings].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PetModule {
    // Repository binding removed. Managed by RepositoryModule.
}

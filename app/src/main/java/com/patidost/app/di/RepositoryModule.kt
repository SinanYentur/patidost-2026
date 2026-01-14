package com.patidost.app.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI
 * Hilt modülü, repository arayüzlerinin somut implementasyonlarını sağlar.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Örnek:
    // @Binds
    // abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}

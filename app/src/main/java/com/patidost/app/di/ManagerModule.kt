package com.patidost.app.di

import com.patidost.app.data.manager.RateLimiterImpl
import com.patidost.app.domain.manager.RateLimiter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun bindRateLimiter(rateLimiterImpl: RateLimiterImpl): RateLimiter
}

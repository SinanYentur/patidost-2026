package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.economy.Subscription
import com.patidost.app.domain.model.economy.SubscriptionStatus
import com.patidost.app.domain.model.economy.SubscriptionType
import com.patidost.app.domain.model.economy.Wallet
import com.patidost.app.domain.repository.EconomyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import javax.inject.Inject

class FakeEconomyRepository @Inject constructor() : EconomyRepository {
    override fun getWallet(): Flow<Resource<Wallet>> = flow {
        emit(Resource.Success(Wallet("fake-user-id", 120, Instant.now())))
    }

    override fun getSubscription(): Flow<Resource<Subscription>> = flow {
        emit(Resource.Success(Subscription(
            userId = "fake-user-id",
            type = SubscriptionType.GOLD_ANNUAL,
            status = SubscriptionStatus.ACTIVE,
            validUntil = Instant.now().plusSeconds(31536000),
            startedAt = Instant.now()
        )))
    }
}

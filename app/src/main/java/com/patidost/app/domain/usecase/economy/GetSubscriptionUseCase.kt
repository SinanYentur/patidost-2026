package com.patidost.app.domain.usecase.economy

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.economy.Subscription
import com.patidost.app.domain.repository.EconomyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSubscriptionUseCase @Inject constructor(
    private val repository: EconomyRepository
) {
    operator fun invoke(): Flow<Resource<Subscription>> {
        return repository.getSubscription()
    }
}

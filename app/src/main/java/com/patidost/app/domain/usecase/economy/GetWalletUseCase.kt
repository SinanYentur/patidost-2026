package com.patidost.app.domain.usecase.economy

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.economy.Wallet
import com.patidost.app.domain.repository.EconomyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWalletUseCase @Inject constructor(
    private val repository: EconomyRepository
) {
    operator fun invoke(): Flow<Resource<Wallet>> {
        return repository.getWallet()
    }
}

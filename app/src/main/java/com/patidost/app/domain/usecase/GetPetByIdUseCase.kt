package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor() {
    operator fun invoke(petId: String): Flow<Resource<Pet>> {
        return flowOf(Resource.Error(null))
    }
}

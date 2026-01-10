package com.patidost.app.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.patidost.app.domain.usecase.pet.RefreshPetsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

/**
 * 🛡️ PetSyncWorker - Sovereign Background Engine.
 * Fixed: Synchronized with DomainResult.isSuccess helper (Rule 100).
 */
@HiltWorker
class PetSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val refreshPetsUseCase: RefreshPetsUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val syncResult = refreshPetsUseCase()
            if (syncResult.isSuccess) {
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            Timber.e(e, "🚨 PetSyncWorker: Failed to synchronize pets")
            Result.failure()
        }
    }
}

package com.patidost.app.domain.model

import androidx.compose.runtime.Immutable
import java.time.Instant

/**
 * Pet Domain Model - V10000.24000 Emotional Singularity Edition.
 * Rule 102: Humble interface, powerful storytelling.
 * RVWL: Integrated behavioral traits and rescue stories for global engagement.
 */
@Immutable
data class Pet(
    val id: String = "",
    val name: String = "",
    val breed: String = "",
    val species: String = "",
    val age: Int = 0,
    val price: Double = 0.0,
    val ownerId: String = "",
    val imageUrl: String = "",
    val videoUrl: String? = null, // Global engagement booster
    val description: String = "",
    val rescueStory: String = "", // Emotional trigger
    val personalityTraits: List<String> = emptyList(), // Personality Match
    val isAdopted: Boolean = false,
    val lastUpdated: Long = Instant.now().toEpochMilli(),
    val syncStatus: SyncStatus = SyncStatus.SYNCED
) {
    companion object {
        val EMPTY = Pet(id = "VOID_PET")
    }
}

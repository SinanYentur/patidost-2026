package com.patidost.app.domain.model

/**
 * SyncStatus - V10000.1000 Sovereign Data State.
 * Tracks the synchronization state between local SSOT and global Firestore.
 */
enum class SyncStatus {
    SYNCED,
    PENDING,
    FAILED,
    DELETED
}

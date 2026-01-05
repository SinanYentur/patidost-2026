package com.patidost.app.data.mapper

import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.domain.model.Pet

/**
 * PetMapper - V10001.00000 Redundancy Cleanup.
 * Rule 102: Minimalist interface.
 * Logic migrated to PetEntity member functions for Sovereign SSOT.
 */

// Extension functions removed to prevent shadowing with PetEntity members.
// Use petEntity.toDomain() and PetEntity.fromDomain(pet) directly.

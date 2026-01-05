package com.patidost.app.util

import com.patidost.app.domain.model.Pet
import java.util.UUID
import java.time.Instant
import java.security.MessageDigest

/**
 * ReceiptGenerator - V10000.30000 Sovereign Proof Engine.
 * Rule 100: Physical evidence of every transaction via SHA-256.
 * RVWL: Generates immutable digital adoption certificates.
 */
object ReceiptGenerator {
    
    fun generateAdoptionReceipt(userId: String, pet: Pet): AdoptionReceipt {
        val timestamp = Instant.now().toEpochMilli()
        val receiptId = "PATI-${UUID.randomUUID().toString().take(8).uppercase()}"
        
        return AdoptionReceipt(
            receiptId = receiptId,
            userId = userId,
            petId = pet.id,
            petName = pet.name,
            timestamp = timestamp,
            verificationHash = calculateIntegrityHash(userId, pet.id, timestamp)
        )
    }

    private fun calculateIntegrityHash(userId: String, petId: String, timestamp: Long): String {
        val input = "$userId:$petId:$timestamp:SOVEREIGN_PATIDOST_KEY_2026"
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

data class AdoptionReceipt(
    val receiptId: String,
    val userId: String,
    val petId: String,
    val petName: String,
    val timestamp: Long,
    val verificationHash: String
)

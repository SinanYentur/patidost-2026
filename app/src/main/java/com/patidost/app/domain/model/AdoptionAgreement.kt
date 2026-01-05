package com.patidost.app.domain.model

import androidx.compose.runtime.Immutable
import java.time.Instant

/**
 * AdoptionAgreement - V10000.24000 HubX+ Ethics Seal.
 * Rule 122: Mandatory Welfare Contract for all adoptions.
 * RVWL: Legal commitment to animal care and return protocol.
 */
@Immutable
data class AdoptionAgreement(
    val agreementId: String = "",
    val adopterId: String = "",
    val petId: String = "",
    val timestamp: Long = Instant.now().toEpochMilli(),
    val isWelfareCommitmentAccepted: Boolean = false,
    val isReturnPolicyAccepted: Boolean = false,
    val isFollowUpAuthorized: Boolean = false,
    val digitalSignature: String = ""
) {
    fun isLegallyBinding(): Boolean = 
        isWelfareCommitmentAccepted && isReturnPolicyAccepted && isFollowUpAuthorized
}

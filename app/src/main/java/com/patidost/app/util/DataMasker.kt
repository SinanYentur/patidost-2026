package com.patidost.app.util

/**
 * DataMasker - V10000.4000 GDPR Privacy Seal.
 * RVWL: Real-time masking of PII (Personal Identifiable Information).
 */
object DataMasker {
    
    fun maskEmail(email: String): String {
        val parts = email.split("@")
        if (parts.size != 2) return "***@***"
        val local = parts[0]
        return if (local.length <= 2) "***@${parts[1]}" 
               else "${local.take(1)}***${local.last()}@${parts[1]}"
    }

    fun maskPhone(phone: String): String {
        return if (phone.length >= 10) "***-***-${phone.takeLast(4)}" 
               else "***-***-****"
    }
}

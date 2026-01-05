package com.patidost.app.util

import com.patidost.app.domain.model.Pet

/**
 * EmergencyProtocol - V10000.25200 HubX+ Safety Seal.
 * Rule 125: Automated emergency detection and redirection.
 */
object EmergencyProtocol {

    private val EMERGENCY_KEYWORDS = listOf(
        "nefes alamıyor", "kanama", "baygın", "krizi",
        "zehir", "çarpma", "düştü", "havale", "acil", "ölüyor"
    )

    fun detectEmergency(text: String): EmergencyLevel {
        val matches = EMERGENCY_KEYWORDS.count { text.contains(it, ignoreCase = true) }
        return when {
            matches >= 2 -> EmergencyLevel.CRITICAL
            matches == 1 -> EmergencyLevel.HIGH
            else -> EmergencyLevel.NONE
        }
    }

    fun getEmergencyMessage(): String {
        return "🚨 ACİL DURUM TESPİT EDİLDİ. Lütfen vakit kaybetmeden en yakın veteriner kliniğine başvurun. Bu uygulama tıbbi tavsiye vermez."
    }
}

enum class EmergencyLevel {
    NONE, HIGH, CRITICAL
}

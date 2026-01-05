package com.patidost.app.util

import java.util.regex.Pattern

/**
 * ChatSecurity - V10000.25000 HubX+ Communication Shield.
 * Rule 112: Mandatory PII Masking in community chat.
 * RVWL: Prevents illegal pet sales and data leakage (Email/Phone).
 */
object ChatSecurity {

    private val PHONE_PATTERN = Pattern.compile("(\\+?[0-9]{1,3})?[0-9]{10}")
    private val EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    fun maskMessage(text: String): String {
        var maskedText = text
        
        // Mask Phone Numbers
        maskedText = PHONE_PATTERN.matcher(maskedText).replaceAll("[TELEFON MASKELEDİ]")
        
        // Mask Emails
        maskedText = EMAIL_PATTERN.matcher(maskedText).replaceAll("[E-POSTA MASKELEDİ]")
        
        // Rule 124: Anti-Trading Keywords
        val forbidden = listOf("satılık", "fiyat", "kaç para", "ucuz", "pahalı")
        forbidden.forEach { word ->
            if (maskedText.contains(word, ignoreCase = true)) {
                maskedText = maskedText.replace(word, "[YASAKLI KELİME]", ignoreCase = true)
            }
        }
        
        return maskedText
    }
}

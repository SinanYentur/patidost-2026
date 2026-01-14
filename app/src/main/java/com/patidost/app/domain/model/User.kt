package com.patidost.app.domain.model

/**
 * 🛡️ GÖREV 0: ANATOMİK BÜTÜNLÜK - İLK HÜCRE
 * Sistemin temel kullanıcı varlığını temsil eder.
 * Bu, tüm kimlik doğrulama ve kullanıcı işlemleri evreninin merkezindeki atomdur.
 */
data class User(
    val id: String,
    val name: String,
    val email: String
)

package com.patidost.app.domain.util

/**
 * 🛡️ GÖREV 0: ANATOMİK BÜTÜNLÜK - ÜÇÜNCÜ HÜCRE
 * Uygulama genelindeki tüm potansiyel hataları modelleyen standart hata ontolojisi.
 * Bu, hataların yönetilebilir ve öngörülebilir olmasını sağlar.
 */
sealed interface AppError {
    val message: String?

    data class NetworkError(override val message: String?) : AppError
    data class DatabaseError(override val message: String?) : AppError
    data class ValidationError(override val message: String?) : AppError
    data class UnknownError(override val message: String?) : AppError
}

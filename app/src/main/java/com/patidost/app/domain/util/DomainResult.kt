package com.patidost.app.domain.util

/**
 * 🛡️ GÖREV 0: ANATOMİK BÜTÜNLÜK - İKİNCİ HÜCRE
 * Domain katmanından dönen sonuçları standartlaştıran zarf.
 * Başarı (Success) veya Hata (Error) durumlarını net bir şekilde modelleyerek
 * UI katmanının bu sonuçları güvenli bir şekilde işlemesini sağlar.
 */
sealed interface DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>
    data class Error(val error: AppError) : DomainResult<Nothing>
}

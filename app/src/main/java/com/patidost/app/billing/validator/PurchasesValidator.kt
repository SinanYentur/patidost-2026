package com.patidost.app.billing.validator

import android.util.Base64
import com.android.billingclient.api.Purchase
import com.patidost.app.BuildConfig
import java.security.*
import java.security.spec.X509EncodedKeySpec
import java.util.*

/**
 * RSA Signature Validator - V10000.11200 Rectified.
 * RVWL: WeakHashMap cache and null-safe validation for 2026 Play Store standards.
 */
class PurchasesValidator(
    private val base64PublicKey: String,
    private val enableLogging: Boolean = BuildConfig.DEBUG
) {
    
    companion object {
        private const val TAG = "PurchasesValidator"
        private const val SIGNATURE_ALGORITHM = "SHA256withRSA"
        private const val KEY_FACTORY_ALGORITHM = "RSA"
        private val publicKeyCache = WeakHashMap<String, PublicKey>()
    }
    
    fun validatePurchase(
        firebaseUserId: String?,
        purchaseToken: String?,
        purchase: Purchase,
        productId: String? = null
    ): ValidationResult {
        if (firebaseUserId.isNullOrEmpty() || purchaseToken.isNullOrEmpty()) {
            return ValidationResult.INVALID_INPUT
        }

        if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED) {
            return ValidationResult.NOT_PURCHASED
        }
        
        return try {
            val publicKey = getPublicKey(base64PublicKey)
            val verified = verify(publicKey, purchase.originalJson, purchase.signature)
            
            if (verified) {
                ValidationResult.VALID
            } else {
                ValidationResult.INVALID_SIGNATURE
            }
        } catch (e: Exception) {
            ValidationResult.VALIDATION_ERROR
        }
    }
    
    private fun getPublicKey(encodedPublicKey: String): PublicKey {
        return publicKeyCache[encodedPublicKey] ?: run {
            val decodedKey = Base64.decode(encodedPublicKey, Base64.DEFAULT or Base64.NO_WRAP)
            val keySpec = X509EncodedKeySpec(decodedKey)
            val key = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM).generatePublic(keySpec)
            publicKeyCache[encodedPublicKey] = key
            key
        }
    }
    
    private fun verify(publicKey: PublicKey, signedData: String, signature: String): Boolean {
        return try {
            val sig = Signature.getInstance(SIGNATURE_ALGORITHM)
            sig.initVerify(publicKey)
            sig.update(signedData.toByteArray(Charsets.UTF_8))
            sig.verify(Base64.decode(signature, Base64.DEFAULT))
        } catch (e: Exception) {
            false
        }
    }

    sealed class ValidationResult {
        object VALID : ValidationResult()
        object INVALID_SIGNATURE : ValidationResult()
        object INVALID_INPUT : ValidationResult()
        object NOT_PURCHASED : ValidationResult()
        object VALIDATION_ERROR : ValidationResult()
    }
}

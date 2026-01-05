package com.patidost.app.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.os.PowerManager
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

/**
 * Sovereign Haptic System - V10000.48000.
 * Rule 100: Based on Android Developers Haptic Feedback Guide.
 * RVWL: Adaptive vibration based on device capability and power state.
 */
class SovereignHapticFeedback(
    private val context: Context,
    private val hapticFeedback: HapticFeedback
) {
    private val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    
    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    enum class FeedbackType {
        LIGHT, MEDIUM, HEAVY, SUCCESS, ERROR
    }

    fun perform(type: FeedbackType) {
        // 🛡️ Mühür: Pil tasarrufunda haptik devre dışı (Premium Davranış)
        if (powerManager.isPowerSaveMode) return

        when (type) {
            FeedbackType.LIGHT -> hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            FeedbackType.MEDIUM -> hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            FeedbackType.HEAVY -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    vibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
                } else {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            }
            FeedbackType.SUCCESS -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    vibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                }
            }
            FeedbackType.ERROR -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    vibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK))
                }
            }
        }
    }
}

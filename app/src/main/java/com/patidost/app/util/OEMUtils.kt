package com.patidost.app.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import timber.log.Timber

/**
 * 🛡️ Rule 300: OEM-Specific Stability Hardening.
 * Prevents "Silent Death" on Chinese ROMs (Xiaomi, Huawei, Oppo).
 */
object OEMUtils {
    fun requestAutostartPermission(context: Context) {
        val manufacturer = Build.MANUFACTURER.lowercase()
        when {
            manufacturer.contains("xiaomi") -> {
                try {
                    val intent = Intent("miui.intent.action.APP_PERM_EDITOR").apply {
                        setClassName("com.miui.securitycenter", 
                                     "com.miui.permcenter.permissions.PermissionsEditorActivity")
                        putExtra("extra_pkgname", context.packageName)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                    Timber.i("🛡️ OEMUtils: Xiaomi autostart requested")
                } catch (e: Exception) {
                    Timber.e(e, "🚨 OEMUtils: Failed to request Xiaomi autostart")
                }
            }
            // 🛡️ Rule: Add Huawei/Oppo specific intents here as per Codex
        }
    }
}

package com.patidost.app.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import timber.log.Timber

/**
 * 🛡️ Rule 300: OEM-Specific Stability Hardening.
 * Fixes "Invisible Death" on Xiaomi, Oppo, Huawei, and Samsung devices.
 * 2026 Standard: Background Sync & Doze Mode bypass.
 */
object OEMWatchdog {
    fun requestAutostart(context: Context) {
        val manufacturer = Build.MANUFACTURER.lowercase()
        when {
            manufacturer.contains("xiaomi") -> requestXiaomi(context)
            manufacturer.contains("oppo") -> requestOppo(context)
            manufacturer.contains("huawei") -> requestHuawei(context)
        }
        requestBatteryOptimizationBypass(context)
    }

    private fun requestXiaomi(context: Context) {
        try {
            val intent = Intent("miui.intent.action.APP_PERM_EDITOR").apply {
                setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
                putExtra("extra_pkgname", context.packageName)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "🚨 OEMWatchdog: Failed to request Xiaomi autostart")
        }
    }

    private fun requestOppo(context: Context) {
        try {
            val intent = Intent().apply {
                setClassName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "🚨 OEMWatchdog: Failed to request Oppo autostart")
        }
    }

    private fun requestHuawei(context: Context) {
        try {
            val intent = Intent().apply {
                setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "🚨 OEMWatchdog: Failed to request Huawei autostart")
        }
    }

    private fun requestBatteryOptimizationBypass(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent()
            val packageName = context.packageName
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                intent.data = Uri.parse("package:$packageName")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                try {
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Timber.e(e, "🚨 OEMWatchdog: Failed to request battery optimization bypass")
                }
            }
        }
    }
}

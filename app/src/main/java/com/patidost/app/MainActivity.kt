package com.patidost.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.metrics.performance.JankStats
import com.patidost.app.ui.navigation.NavGraph
import com.patidost.app.ui.theme.PatidostTheme
import com.patidost.app.util.ShadowAuditEngine
import com.patidost.app.util.OEMWatchdog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 🚀 2026 SOVEREIGN GATEWAY v3.0 - ENTERPRISE ELITE
 * Mühür: Android 15 Edge-to-Edge, Predictive Back, Anti-Leak JankStats.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var auditEngine: ShadowAuditEngine
    private var jankStats: JankStats? = null

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) auditEngine.logEvent("notification_permission_denied")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 🛡️ Rule 300: 2026 System Requirements
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        // 🛡️ Mühür: Leak-Proof Jank Monitoring
        jankStats = JankStats.createAndTrack(window) { frameData ->
            auditEngine.reportJankData(frameData.frameDurationUiNanos)
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkAndRequestNotificationPermission()
        }

        // 🚀 Android 15+ Predictive Back Gesture
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Logic for predictive back animation
                    finish()
                }
            })
        }

        setContent {
            PatidostTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
            }
        }
        
        OEMWatchdog.requestAutostart(this)
    }

    private fun checkAndRequestNotificationPermission() {
        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    override fun onResume() {
        super.onResume()
        jankStats?.isTrackingEnabled = true
        auditEngine.startAudit()
    }

    override fun onPause() {
        super.onPause()
        jankStats?.isTrackingEnabled = false
        auditEngine.stopAudit()
    }

    override fun onDestroy() {
        // 🛡️ Mutlak Mühür: Prevent Memory Leaks
        jankStats?.close()
        jankStats = null
        super.onDestroy()
    }
}

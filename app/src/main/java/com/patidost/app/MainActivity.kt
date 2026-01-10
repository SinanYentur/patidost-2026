package com.patidost.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.patidost.app.ui.navigation.NavGraph
import com.patidost.app.ui.theme.PatidostTheme
import com.patidost.app.util.ShadowAuditEngine
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 🚀 2026 SOVEREIGN GATEWAY - RECONSTRUCTED
 * Fixes Black Screen by ensuring deterministic UI lifecycle.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var auditEngine: ShadowAuditEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            PatidostTheme {
                // 🛡️ Mühür: Arka plan rengini zorunlu kılıyoruz (Siyah Ekran Savunması)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
            }
        }
        
        auditEngine.startAudit()
    }
}

package com.patidost.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.patidost.app.ui.navigation.NavGraph
import com.patidost.app.ui.theme.PatidostTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity - 2026 Android 16 (API 36) PRODUCTION Standard.
 * RVWL: Corrected package name and edge-to-edge integration.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // API 36 Mandatory Requirement
        setContent {
            PatidostTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

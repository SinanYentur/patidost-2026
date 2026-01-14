package com.patidost.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.patidost.app.presentation.navigation.NavGraph
import com.patidost.app.presentation.ui.main.MainViewModel
import com.patidost.app.presentation.theme.PatidostTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.uiState.value.isLoading
        }
        
        setContent {
            PatidostTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = viewModel.uiState.value
                    if (!uiState.isLoading && uiState.startDestination != null) {
                        val navController = rememberNavController()
                        NavGraph(navController = navController, startDestination = uiState.startDestination)
                    }
                }
            }
        }
    }
}

package com.patidost.app.ui.screen.auth

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Auth Screen - V46.70 Constitutional Alignment.
 * RVWL: Synchronized with AuthViewModel.onAuthAction and AuthContent.
 */
@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // AuthContent is now correctly implemented in AuthComponents.kt
    AuthContent(
        uiState = uiState,
        onAction = { email, password, isSignUp, name -> 
            // Rule 97 Evidence: Method name is onAuthAction in AuthViewModel.kt
            viewModel.onAuthAction(email, password, name)
            
            // Side effect: Handle navigation success via state observation if needed,
            // or pass onAuthSuccess if ViewModel supports it.
        }
    )

    // Rule 92: Observe success state for navigation
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Authenticated) {
            onAuthSuccess()
        }
    }
}

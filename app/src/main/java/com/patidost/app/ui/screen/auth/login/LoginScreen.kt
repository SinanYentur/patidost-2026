package com.patidost.app.ui.screen.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.ui.screen.auth.AuthUiState
import com.patidost.app.ui.screen.auth.AuthViewModel

/**
 * 🚀 PATIDOST LOGIN SCREEN v4.3 - FINAL PRODUCTION SEAL
 * Rule 100: Linked with production LoginContent components.
 * Rule 110: Sealed with centralized AuthViewModel.
 */
@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // 🛡️ Mühür: Gerçek Production LoginContent çağrısı
        LoginContent(
            uiState = uiState,
            onEmailAction = { email, pass, isSignUp, name ->
                viewModel.onAuthAction(email, pass, name.takeIf { isSignUp })
            },
            onGoogleSignIn = { /* V2 Feature */ },
            onFacebookSignIn = { /* V2 Feature */ },
            onToggleMode = { /* Managed Internally in Content */ }
        )
    }

    // 🛡️ Mühür: Başarılı Giriş Takibi
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Authenticated) {
            onAuthSuccess()
        }
    }
}

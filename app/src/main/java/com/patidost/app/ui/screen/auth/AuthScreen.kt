package com.patidost.app.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patidost.app.ui.component.core.SovereignScreenState

/**
 * 🛡️ AuthScreen - Sovereign Identity Gate V10000.70024.
 * Rule 420: Restored from Purge and connected to physical AuthContent.
 * V2: Implemented signUp logic to call the viewModel.
 * V3: Pass 'name' to signUp to fix build error.
 */
@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: (String) -> Unit
) {
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    // 🛡️ Mühür: Oturum açma başarılıysa navigasyonu tetikle
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onAuthSuccess((authState as AuthState.Success).userName)
        }
    }

    SovereignScreenState(
        isLoading = authState is AuthState.Loading,
        error = if (authState is AuthState.Error) (authState as AuthState.Error).message else null
    ) {
        AuthContent(
            uiState = if (authState is AuthState.Loading) AuthUiState.Loading else AuthUiState.Idle,
            onAction = { email, pass, isSignUp, name ->
                if (isSignUp) {
                    viewModel.signUp(email, pass, name)
                } else {
                    viewModel.signIn(email, pass)
                }
            }
        )
    }
}

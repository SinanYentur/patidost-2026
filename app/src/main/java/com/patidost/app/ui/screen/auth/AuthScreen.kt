package com.patidost.app.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI - BEDENİN YARATILIŞI
 * AuthViewModel'in beynini barındıran ve onunla etkileşime giren UI katmanı (Composable).
 * Bu ekran, anayasal MVVM döngüsünü tamamlar.
 */
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Tek seferlik olayları dinle (Anayasa Madde 2)
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AuthEvent.ShowError -> {
                    // Burada bir Snackbar veya Toast gösterilecek.
                }
                is AuthEvent.NavigateToHome -> {
                    onNavigateToHome()
                }
            }
        }
    }

    // UI'nin kendisi (TextFields, Buttons vb.) buraya inşa edilecek.
    // Bu yapı, `uiState`'e göre kendini güncelleyecek.
    // Örn: uiState is AuthState.Loading -> CircularProgressIndicator göster.

}

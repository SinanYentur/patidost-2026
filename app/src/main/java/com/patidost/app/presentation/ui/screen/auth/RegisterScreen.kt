package com.patidost.app.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.patidost.app.presentation.ui.component.CustomInputField
import com.patidost.app.presentation.ui.component.PremiumButton

@Composable
fun RegisterScreen(
    // onNavigateToLogin: () -> Unit, // Will be used for navigation
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hesap Oluştur",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomInputField(
                value = name,
                onValueChange = { name = it },
                label = "Ad Soyad",
                leadingIcon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomInputField(
                value = email,
                onValueChange = { email = it },
                label = "E-posta",
                leadingIcon = Icons.Default.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomInputField(
                value = password,
                onValueChange = { password = it },
                label = "Şifre",
                leadingIcon = Icons.Default.Lock,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(24.dp))

            PremiumButton(
                text = "Kayıt Ol",
                onClick = { /* TODO: ViewModel Register Logic */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Zaten bir hesabın var mı?", style = MaterialTheme.typography.bodyMedium)
                TextButton(onClick = { /* onNavigateToLogin() */ }) {
                    Text("Giriş Yap", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

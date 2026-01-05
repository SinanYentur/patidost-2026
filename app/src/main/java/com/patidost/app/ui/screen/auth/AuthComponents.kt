package com.patidost.app.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.PatiGold

@Composable
fun AuthContent(
    uiState: AuthUiState,
    onAction: (String, String, Boolean, String) -> Unit
) {
    var isSignUp by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GlassCard(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (isSignUp) "Aramıza Katıl" else "Hoş Geldin",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = PatiGold
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                if (isSignUp) {
                    AuthTextField(value = name, onValueChange = { name = it }, label = "İsim", imeAction = androidx.compose.ui.text.input.ImeAction.Next, onImeAction = {})
                    Spacer(modifier = Modifier.height(16.dp))
                }

                AuthTextField(value = email, onValueChange = { email = it }, label = "E-posta", imeAction = androidx.compose.ui.text.input.ImeAction.Next, onImeAction = {})
                Spacer(modifier = Modifier.height(16.dp))
                AuthTextField(value = password, onValueChange = { password = it }, label = "Şifre", isPassword = true, imeAction = androidx.compose.ui.text.input.ImeAction.Done, onImeAction = { onAction(email, password, isSignUp, name) })

                Spacer(modifier = Modifier.height(32.dp))

                PremiumPatiButton(
                    text = if (isSignUp) "Kayıt Ol" else "Giriş Yap",
                    onClick = { onAction(email, password, isSignUp, name) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextButton(onClick = { isSignUp = !isSignUp }) {
                    Text(
                        text = if (isSignUp) "Zaten hesabım var" else "Yeni hesap oluştur",
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                if (uiState is AuthUiState.Loading) {
                    CircularProgressIndicator(color = PatiGold)
                }
            }
        }
    }
}

// Keeping the existing AuthTextField below
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imeAction: androidx.compose.ui.text.input.ImeAction,
    onImeAction: () -> Unit,
    keyboardType: androidx.compose.ui.text.input.KeyboardType = androidx.compose.ui.text.input.KeyboardType.Text,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White.copy(alpha = 0.5f)) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        visualTransformation = if (isPassword) androidx.compose.ui.text.input.PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = androidx.compose.foundation.text.KeyboardActions(onAny = { onImeAction() }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = PatiGold,
            unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
            cursorColor = PatiGold
        )
    )
}

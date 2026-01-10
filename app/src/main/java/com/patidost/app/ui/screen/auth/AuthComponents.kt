package com.patidost.app.ui.screen.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.R
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.PatiGold

/**
 * 🛡️ AuthComponents - V10011.70154 Industrial Master Seal.
 * Rule 100: Play Store Quality Guidelines (Data Integrity, Premium UX).
 * V2: Visual alignment with official "Kayıt Ol" and "Giriş Yap" screen designs.
 */
@Composable
fun AuthContent(
    uiState: AuthUiState,
    onAction: (String, String, Boolean, String) -> Unit
) {
    var isSignUp by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    LaunchedEffect(isSignUp) {
        email = ""
        password = ""
        name = ""
    }

    BackHandler(enabled = isSignUp) {
        isSignUp = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Placeholder logo
                contentDescription = "Patidost Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isSignUp) "Kayıt Ol" else "Birliktelik Başlasın!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PatiGold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isSignUp) "Ücretsiz hesap oluştur ve evcil hayvan topluluğumuza katıl!" else "Kaydol ve evcil dostlarınla daha mutlu anlar paylaş.",
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState is AuthUiState.Error) {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            // Social Buttons
            Button(onClick = { /* TODO: Google Sign In */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Google ile Devam Et")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* TODO: Facebook Sign In */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Facebook ile Devam Et")
            }
            
            Text("VEYA", modifier = Modifier.padding(vertical = 12.dp), color = Color.White.copy(alpha = 0.7f))


            if (isSignUp) {
                AuthTextField(
                    value = name,
                    onValueChange = { input ->
                        val cleanInput = input.trimStart().replace("  ", " ")
                        if (cleanInput.length <= 50) name = cleanInput.filter { it.isLetter() || it.isWhitespace() }
                    },
                    label = "Ad",
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                    onImeAction = {},
                    autoCorrect = true
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            AuthTextField(
                value = email,
                onValueChange = { input ->
                    val cleanInput = input.trimStart().filter { !it.isWhitespace() && it.code < 128 }
                    if (cleanInput.length <= 100) email = cleanInput
                },
                label = "E-Posta",
                imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                onImeAction = {},
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Email,
                autoCorrect = false
            )
            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = password,
                onValueChange = { input ->
                    val cleanInput = input.filter { !it.isWhitespace() && it.code < 128 }
                    if (cleanInput.length <= 32) password = cleanInput
                },
                label = "Şifre",
                isPassword = true,
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                onImeAction = { onAction(email.trim(), password, isSignUp, name.trim()) },
                autoCorrect = false
            )

            Spacer(modifier = Modifier.height(24.dp))

            PremiumPatiButton(
                text = if (isSignUp) "Kayıt Ol" else "Giriş Yap",
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        onAction(email.trim(), password, isSignUp, name.trim())
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(onClick = { isSignUp = !isSignUp }) {
                Text(
                    text = if (isSignUp) "Zaten bir hesabın var mı? Giriş Yap" else "Hesabın yok mu? Kayıt Ol",
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Kayıt olarak, Gizlilik Politikası ve Kullanıcı Şartları'nı kabul ediyorsunuz.",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(color = PatiGold, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imeAction: androidx.compose.ui.text.input.ImeAction,
    onImeAction: () -> Unit,
    keyboardType: androidx.compose.ui.text.input.KeyboardType = androidx.compose.ui.text.input.KeyboardType.Text,
    isPassword: Boolean = false,
    autoCorrect: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White.copy(alpha = 0.5f)) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        visualTransformation = if (isPassword && !passwordVisible)
            androidx.compose.ui.text.input.PasswordVisualTransformation()
        else
            androidx.compose.ui.text.input.VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Şifre Görünürlüğü",
                        tint = if (passwordVisible) PatiGold else Color.White,
                        modifier = Modifier
                            .size(18.dp)
                            .alpha(if (passwordVisible) 1f else 0.3f)
                    )
                }
            }
        },
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
            autoCorrectEnabled = autoCorrect
        ),
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

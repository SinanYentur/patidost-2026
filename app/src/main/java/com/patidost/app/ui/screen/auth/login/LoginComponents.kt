package com.patidost.app.ui.screen.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.ui.screen.auth.AuthUiState
import com.patidost.app.ui.theme.*

/**
 * 🚀 PATIDOST LOGIN COMPONENTS v4.2 - FULL PRODUCTION
 * Rule 100: Precise UI replica of the Tailwind/HTML design.
 * Rule 110: Unified state management with AuthUiState.
 */
@Composable
fun LoginContent(
    uiState: AuthUiState,
    onEmailAction: (String, String, Boolean, String) -> Unit,
    onGoogleSignIn: () -> Unit,
    onFacebookSignIn: () -> Unit,
    onToggleMode: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var isSignUpMode by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(calculateSnowyGradient()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🐾 Logo Section (HTML Replica)
            Icon(
                imageVector = Icons.Filled.Pets,
                contentDescription = null,
                tint = PatiGold,
                modifier = Modifier.size(64.dp)
            )
            
            Text(
                text = "PATİDOST",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Black,
                color = TextDark,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 🛡️ Premium Login Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 400.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp), 
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isSignUpMode) "Yeni Dostluklar İçin" else "Tekrar Hoş Geldin",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))

                    if (isSignUpMode) {
                        SovereignInputV2(
                            value = name,
                            onValueChange = { name = it },
                            label = "İsim"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    SovereignInputV2(
                        value = email,
                        onValueChange = { email = it },
                        label = "E-posta"
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SovereignInputV2(
                        value = password,
                        onValueChange = { password = it },
                        label = "Şifre",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // 🛡️ Loading & Action
                    if (uiState is AuthUiState.Loading) {
                        CircularProgressIndicator(color = PatiGold)
                    } else {
                        Button(
                            onClick = { onEmailAction(email, password, isSignUpMode, name) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PatiGold)
                        ) {
                            Text(
                                text = if (isSignUpMode) "Kayıt Ol" else "Giriş Yap",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    if (uiState is AuthUiState.Error) {
                        Text(
                            text = uiState.message, 
                            color = Color.Red, 
                            fontSize = 12.sp, 
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { isSignUpMode = !isSignUpMode }) {
                        Text(
                            text = if (isSignUpMode) "Zaten bir hesabın var mı? Giriş yap" else "Henüz dostumuz değil misin? Kayıt ol",
                            color = TextGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SovereignInputV2(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = TextGray) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) androidx.compose.ui.text.input.PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PatiGold,
            unfocusedBorderColor = GlassBorder,
            focusedTextColor = TextDark,
            unfocusedTextColor = TextDark
        )
    )
}

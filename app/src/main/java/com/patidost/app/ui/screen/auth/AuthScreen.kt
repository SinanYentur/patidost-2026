package com.patidost.app.ui.screen.auth

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.R
import com.patidost.app.ui.component.patidostGlassCard
import com.patidost.app.ui.theme.*

/**
 * Premium Authentication Screen - Mimar V44 / API 36 Standard.
 * RVWL: Synchronized with Visual Asset 5 (Gold & Night Glass UI).
 */
@Composable
fun AuthScreen(
    state: AuthUiState,
    onAuthAction: (String, String, String) -> Unit,
    onToggleMode: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NightDeep)
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            // Background Glow (Visual Parity with Asset 5)
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Brush.radialGradient(listOf(GoldPrimary.copy(alpha = 0.15f), Color.Transparent)))
                    .align(Alignment.TopCenter)
            )

            AuthContent(
                state = state,
                onAuthAction = onAuthAction,
                onToggleMode = onToggleMode
            )
        }
    }
}

@Composable
private fun AuthContent(
    state: AuthUiState,
    onAuthAction: (String, String, String) -> Unit,
    onToggleMode: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var isLoginMode by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .patidostGlassCard() // Mimar V44 Premium Glass Modifier
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isLoginMode) "PatiDost" else stringResource(R.string.register),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = GoldPrimary,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        if (!isLoginMode) {
            AuthTextField(
                value = name,
                onValueChange = { name = it },
                label = stringResource(R.string.name),
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.email),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            onImeAction = { focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            onImeAction = { focusManager.clearFocus() },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        val isValid = validateAuthInput(email, password)

        Button(
            onClick = { onAuthAction(email, password, name) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = state !is AuthUiState.Loading && isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = GoldPrimary,
                contentColor = NightDeep,
                disabledContainerColor = GoldPrimary.copy(alpha = 0.3f)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            if (state is AuthUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = NightDeep, strokeWidth = 2.dp)
            } else {
                Text(
                    if (isLoginMode) stringResource(R.string.login) else stringResource(R.string.register),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        TextButton(onClick = { 
            isLoginMode = !isLoginMode
            onToggleMode() 
        }) {
            Text(
                text = if (isLoginMode) stringResource(R.string.no_account) else stringResource(R.string.have_account),
                color = Color.White.copy(alpha = 0.6f)
            )
        }

        if (state is AuthUiState.Error) {
            Text(
                text = stringResource(state.messageResId),
                color = Color.Red.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White.copy(alpha = 0.5f)) },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(onAny = { onImeAction() }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = GoldPrimary,
            unfocusedBorderColor = GlassStroke,
            cursorColor = GoldPrimary
        )
    )
}

private fun validateAuthInput(email: String, pass: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && pass.length >= 6
}

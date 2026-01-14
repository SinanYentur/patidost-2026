package com.patidost.app.presentation.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.patidost.app.presentation.ui.util.UiText

@Composable
fun RegisterScreen(viewModel: AuthViewModel) {
    val uiState by viewModel.stateManager.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kayıt Ol")
        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = uiState.name,
            onValueChange = { viewModel.onEvent(AuthEvent.NameChanged(it)) },
            label = { Text("Ad Soyad") },
            leadingIcon = { Icons.Default.Person },
            isError = uiState.nameError != null,
            readOnly = uiState.isLoading,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { emailFocusRequester.requestFocus() })
        )
        uiState.nameError?.let { Text(text = it.asString()) }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.focusRequester(emailFocusRequester),
            value = uiState.email,
            onValueChange = { viewModel.onEvent(AuthEvent.EmailChanged(it)) },
            label = { Text("E-posta") },
            leadingIcon = { Icons.Default.Email },
            isError = uiState.emailError != null,
            readOnly = uiState.isLoading,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
        )
        uiState.emailError?.let { Text(text = it.asString()) }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.focusRequester(passwordFocusRequester),
            value = uiState.password,
            onValueChange = { viewModel.onEvent(AuthEvent.PasswordChanged(it)) },
            label = { Text("Şifre") },
            leadingIcon = { Icons.Default.Lock },
            visualTransformation = PasswordVisualTransformation(),
            isError = uiState.passwordError != null,
            readOnly = uiState.isLoading,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { 
                focusManager.clearFocus()
                viewModel.onEvent(AuthEvent.Register)
            })
        )
        uiState.passwordError?.let { Text(text = it.asString()) }

        uiState.generalError?.let { Text(text = it.asString()) }

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { 
                    focusManager.clearFocus()
                    viewModel.onEvent(AuthEvent.Register) 
                },
                enabled = !uiState.isLoading
            ) {
                Text("Kayıt Ol")
            }
        }
    }
}

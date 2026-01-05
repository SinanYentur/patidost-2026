package com.sovereign.app.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sovereign.app.ui.theme.Dimens

/**
 * 🛡️ SOVEREIGN UI ARMOR v1.0
 * Rule: No raw Material3 components in screens.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = Dimens.MinTouchTarget),
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(Dimens.RadiusLarge),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        if (isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
        else Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AppScreenState(
    isLoading: Boolean,
    error: String? = null,
    isEmpty: Boolean = false,
    onRetry: () -> Unit = {},
    content: @Composable () -> Unit
) {
    when {
        isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        error != null -> AppErrorState(message = error, onRetry = onRetry)
        isEmpty -> AppEmptyState()
        else -> content()
    }
}

@Composable
fun AppErrorState(message: String, onRetry: () -> Unit) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("⚠️", style = MaterialTheme.typography.displayLarge)
        Text(message)
        AppButton(text = "Retry", onClick = onRetry)
    }
}

@Composable
fun AppEmptyState() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("No data available.", style = MaterialTheme.typography.bodyLarge)
    }
}

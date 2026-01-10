package com.patidost.app.ui.component.core

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.patidost.app.ui.theme.AthenaPurple

/**
 * 🛡️ Rule 100: Mandatory UI Components for Premium Experience.
 */
@Composable
fun PremiumPatiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = AthenaPurple)
    ) {
        Text(text = text)
    }
}

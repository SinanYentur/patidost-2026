package com.patidost.app.ui.screen.cart.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.ui.component.core.SovereignScreenState
import com.patidost.app.ui.component.core.SovereignButton

/**
 * 🛡️ CartScreen - V10000.70030 Sovereign Anchor.
 * Rule 420: Fixed missing dp import to stabilize UI compilation.
 */
@Composable
fun CartScreen(
    onCheckoutClick: () -> Unit
) {
    SovereignScreenState(
        isLoading = false,
        isEmpty = true 
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sahiplenme Sepetiniz Boş", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            SovereignButton(
                text = "Dostları Keşfet",
                onClick = onCheckoutClick
            )
        }
    }
}

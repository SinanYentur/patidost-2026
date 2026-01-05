package com.patidost.app.ui.screen.cart.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.domain.model.Pet
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.PatiGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text("Sepetim", fontWeight = FontWeight.Bold, color = PatiGold) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = PatiGold)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = PatiGold,
            navigationIconContentColor = PatiGold
        )
    )
}

@Composable
fun CartContent(
    uiState: CartUiState,
    padding: PaddingValues,
    onRemoveItem: (String) -> Unit,
    onCheckout: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = PatiGold)
        } else if (uiState.error != null) {
            Text(uiState.error, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        } else if (uiState.cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Sepetiniz boş", color = Color.White.copy(alpha = 0.5f))
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.cartItems, key = { it.id }) { pet ->
                        CartItemCard(pet = pet, onRemove = { onRemoveItem(pet.id) })
                    }
                }
                
                CartSummaryPanel(totalPrice = uiState.totalAmount, onCheckout = onCheckout)
            }
        }
    }
}

@Composable
fun CartItemCard(pet: Pet, onRemove: () -> Unit) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(pet.name, color = PatiGold, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(pet.breed, color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
            }
            Text("${pet.price} TL", color = Color.White, fontWeight = FontWeight.ExtraBold)
            IconButton(onClick = onRemove) { Text("✕", color = Color.Red, fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
fun CartSummaryPanel(totalPrice: Double, onCheckout: () -> Unit) {
    GlassCard(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Toplam", color = Color.White.copy(alpha = 0.7f))
                Text("$totalPrice TL", color = PatiGold, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            PremiumPatiButton(text = "Ödemeyi Tamamla", onClick = onCheckout, modifier = Modifier.fillMaxWidth())
        }
    }
}

package com.patidost.app.presentation.ui.screen.purchase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GoldPlansScreen(viewModel: GoldViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gold Üyelik Planları", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(uiState.plans) { plan ->
                PlanCard(plan = plan, isSelected = uiState.selectedPlan?.productId == plan.productId) {
                    viewModel.onEvent(GoldPurchaseEvent.SelectPlan(plan))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.onEvent(GoldPurchaseEvent.Purchase) },
                enabled = uiState.selectedPlan != null
            ) {
                Text("Satın Al")
            }
        }
        
        uiState.purchaseError?.let {
            Text(text = it.asString(), color = MaterialTheme.colorScheme.error)
        }
        
        if(uiState.isPurchaseSuccess) {
             Text("Satın alma başarılı!", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun PlanCard(
    plan: SubscriptionPlanUiModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = if (isSelected) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                 else CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(plan.title, style = MaterialTheme.typography.titleLarge)
                Text(plan.description, style = MaterialTheme.typography.bodyMedium)
            }
            Text(plan.price, style = MaterialTheme.typography.titleMedium)
        }
    }
}

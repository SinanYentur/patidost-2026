package com.patidost.app.presentation.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patidost.app.domain.model.Pet

@Composable
fun PetDetailContent(pet: Pet, onGivePatiPoints: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = pet.name)
        Text(text = pet.breed)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onGivePatiPoints) {
            Text(text = "Pati Puanı Gönder")
        }
    }
}

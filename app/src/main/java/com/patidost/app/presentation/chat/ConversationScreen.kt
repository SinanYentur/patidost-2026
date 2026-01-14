
package com.patidost.app.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.patidost.app.presentation.ui.theme.PatidostTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen(userName: String) {
    PatidostTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(userName) }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        // Mesajlar burada listelenecek
                    }
                    // Mesaj gönderme alanı buraya gelecek
                }
            }
        )
    }
}

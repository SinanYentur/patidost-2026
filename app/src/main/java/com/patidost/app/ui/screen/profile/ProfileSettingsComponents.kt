package com.patidost.app.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patidost.app.domain.model.User
import com.patidost.app.ui.component.GlassCard
import com.patidost.app.ui.component.PremiumPatiButton
import com.patidost.app.ui.theme.*
import com.patidost.app.util.calculateSnowyGradient

/**
 * 🛡️ ProfileSettingsComponents - V10000.70042 Sovereign Cleanse.
 * Rule 310: Physical synchronization with ViewUtils.kt.
 * Fixed: Removed accidental text injection from source code.
 */
@Composable
fun ProfileSettingsContent(
    user: User?,
    onSave: (User) -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(calculateSnowyGradient())
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profil Ayarları", 
                style = MaterialTheme.typography.headlineMedium, 
                color = TextDark,
                fontWeight = FontWeight.Black
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(24.dp)) {
                    if (user != null) {
                        Text("Kullanıcı Adı: ${user.name}", color = TextDark)
                        Text("E-posta: ${user.email}", color = TextGray)
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        PremiumPatiButton(
                            text = "Değişiklikleri Kaydet",
                            onClick = { onSave(user) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text("Yükleniyor...", color = TextGray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(onClick = onBackClick) {
                Text("Geri Dön", color = TextGray)
            }
        }
    }
}

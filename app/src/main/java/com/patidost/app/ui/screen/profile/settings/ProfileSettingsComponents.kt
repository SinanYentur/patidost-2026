package com.patidost.app.ui.screen.profile.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.patidost.app.domain.model.User
import com.patidost.app.ui.theme.PatiGold

/**
 * Profile Settings Components - V10000.12000 GDPR Sovereign Edition.
 * Rule 105: Zero Void in Legal/Compliance layers.
 * RVWL: Integrated GDPR Art. 17 (Delete) and Art. 20 (Export) actions.
 */
@Composable
fun ProfileSettingsContent(
    user: User?,
    onSave: (User) -> Unit,
    onExportData: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profil ve Güvenlik", 
            style = MaterialTheme.typography.headlineMedium, 
            fontWeight = FontWeight.ExtraBold,
            color = PatiGold
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        // Basic Profile Info (Placeholder for form)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("E-posta: ${user?.email ?: "..."}")
                Text("İsim: ${user?.name ?: "..."}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { user?.let { onSave(it) } },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PatiGold)
        ) {
            Icon(Icons.Default.Save, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Değişiklikleri Kaydet")
        }

        Spacer(modifier = Modifier.weight(1f))

        // --- SOVEREIGN GDPR SECTION ---
        Divider(color = Color.White.copy(alpha = 0.1f))
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(
            onClick = onExportData,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Icon(Icons.Default.Download, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Verilerimi Dışa Aktar (GDPR Art. 20)")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onDeleteAccount,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f))
        ) {
            Icon(Icons.Default.DeleteForever, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Hesabımı Kalıcı Olarak Sil (GDPR Art. 17)")
        }
        
        Text(
            text = "Hesabınızı sildiğinizde tüm verileriniz 30 gün içinde fiziksel olarak imha edilecektir.",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

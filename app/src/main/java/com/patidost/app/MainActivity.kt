package com.patidost.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

/**
 * 🛡️ SİSTEMİK ENTEGRASYON - HAYALET BEDENİN YARATILIŞI
 * Projenin ana giriş noktası.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Ana UI (örn: Navigasyon grafiği) buraya gelecek.
        }
    }
}

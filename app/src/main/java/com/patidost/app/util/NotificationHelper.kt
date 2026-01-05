package com.patidost.app.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.patidost.app.R

/**
 * NotificationHelper - V10000.17800 Sovereign Engagement Edition.
 * Rule 115: Personalized, category-based notifications. 
 * RVWL: Balances community engagement with user tranquility.
 */
class NotificationHelper(private val context: Context) {

    companion object {
        private const val SOCIAL_CHANNEL_ID = "patidost_social_updates"
        private const val PRESTIGE_CHANNEL_ID = "patidost_prestige_pets"
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(NotificationManager::class.java)
            
            // Channel 1: Daily Community Updates (Default Importance)
            val socialChannel = NotificationChannel(
                SOCIAL_CHANNEL_ID,
                "Topluluk Güncellemeleri",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Yeni eklenen dostlar ve tanıdık bildirimleri."
            }

            // Channel 2: High Interest / Prestige Pets (High Importance)
            val prestigeChannel = NotificationChannel(
                PRESTIGE_CHANNEL_ID,
                "Öne Çıkan Dostlar",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Özel ırklar ve çok beğenilen dostluk hikayeleri."
                enableVibration(true)
            }

            manager.createNotificationChannel(socialChannel)
            manager.createNotificationChannel(prestigeChannel)
        }
    }

    /**
     * Shows a noble notification for new pet additions.
     * @param userName Name of the user who added the pet (e.g., Barış E.)
     * @param petDescription Description including breed (e.g., Van Kedisi)
     * @param thumbnail Optional profile or pet image to show as large icon.
     */
    fun showNewPetNotification(
        userName: String, 
        petDescription: String, 
        thumbnail: Bitmap? = null
    ) {
        val title = "Yeni Bir Dostluk İlanı"
        val message = "$userName, asil bir $petDescription için yuva arıyor. Belki de beklediğin dost budur?"

        val builder = NotificationCompat.Builder(context, SOCIAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(thumbnail) // Sade ve dairesel thumbnail
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}

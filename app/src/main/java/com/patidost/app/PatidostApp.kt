package com.patidost.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.ktx.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp

/**
 * Patidost Application Class - 2026 PRODUCTION Standard.
 * RVWL: Corrected package name and security initialization.
 */
@HiltAndroidApp
class PatidostApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase Security Layer
        FirebaseApp.initializeApp(this)
        
        val appCheckFactory = if (BuildConfig.DEBUG) {
            DebugAppCheckProviderFactory.getInstance()
        } else {
            PlayIntegrityAppCheckProviderFactory.getInstance()
        }
        
        Firebase.appCheck.installAppCheckProviderFactory(appCheckFactory)
    }
}

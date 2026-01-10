package com.patidost.app.initializers

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.FirebaseApp
import com.google.firebase.perf.FirebasePerformance
import com.patidost.app.BuildConfig
import timber.log.Timber

/**
 * 🛡️ Rule 300: Cold-start optimization using Jetpack Startup.
 * Fixed: Firebase Performance collection property name.
 */
class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        FirebaseApp.initializeApp(context)
        
        // 🚨 Mühür: Firebase Performance Collection Standard
        FirebasePerformance.getInstance().isPerformanceCollectionEnabled = !BuildConfig.DEBUG
        
        val trace = FirebasePerformance.getInstance().newTrace("sovereign_startup_trace")
        trace.start()
        // Critical warm-up code here
        trace.stop()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

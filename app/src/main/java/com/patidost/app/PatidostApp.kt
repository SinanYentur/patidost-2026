package com.patidost.app

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.github.anrwatchdog.ANRWatchDog
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.patidost.app.util.CrashlyticsTree
import com.patidost.app.util.SecurityGuard
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * 🛡️ PatidostApp - V10000.80000 Sovereign Core.
 * Rule 300.4: Replaced custom OEMWatchdog with industry-standard ANRWatchDog.
 */
@HiltAndroidApp
class PatidostApp : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        // 🛡️ Rule 300.4: ANR Watchdog activated for main thread monitoring.
        ANRWatchDog().start()

        setupLogging()
        FirebaseApp.initializeApp(this)
        setupAppCheck()

        Timber.i("🚀 Sovereign Core Engine: Fully Operational")
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }

    private fun setupAppCheck() {
        val appCheck = FirebaseAppCheck.getInstance()
        if (BuildConfig.DEBUG) {
            appCheck.installAppCheckProviderFactory(DebugAppCheckProviderFactory.getInstance())
        } else {
            appCheck.installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance())
        }
    }
}

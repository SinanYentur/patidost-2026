package com.patidost.app.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

/**
 * 🛡️ Rule 110 & 190: Production Logging Tree.
 * Routes high-priority logs and exceptions to Firebase Crashlytics.
 */
class CrashlyticsTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCustomKey("priority", priority)
        tag?.let { crashlytics.setCustomKey("tag", it) }
        crashlytics.log(message)

        if (t != null) {
            crashlytics.recordException(t)
        }
    }
}

package com.patidost.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * 🛡️ Rule 100: Mandatory Hilt Test Runner for CI/CD.
 * Ensures Hilt is properly initialized in instrumentation tests.
 * ARTICLE 13: Physical sync with Hilt 2.54 Testing DNA.
 */
class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}

package com.patidost.app.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Baseline Profile Generator.
 * RVWL: Optimized for Android 16 (API 36) startup paths.
 * WEB PROOF: https://developer.android.com/topic/performance/baselineprofiles/create-baselineprofile
 */
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() = baselineProfileRule.collect(
        packageName = "com.patidost.app",
        // Check: Ensure the app is correctly started and its critical paths are executed
        profileBlock = {
            startActivityAndWait()
        }
    )
}

package com.patidost.app.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 🛡️ Rule 300: Performance Hardening.
 * This class generates the Baseline Profile to achieve < 800ms cold start.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() = baselineProfileRule.collect(
        packageName = "com.patidost.app",
        includeInAppScreenshot = true
    ) {
        pressHome()
        startActivityAndWait()
        
        // 🎯 Sovereign Journey: Key interaction paths
        device.waitForIdle()
    }
}

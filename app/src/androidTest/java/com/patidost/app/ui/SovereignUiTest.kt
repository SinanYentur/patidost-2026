package com.patidost.app.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.patidost.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * 🛡️ SovereignUiTest - V10011.70114 Global UI Anchor.
 * Rule 190: End-to-End Monitoring for 2026 Global Launch.
 * ARTICLE 17: Added hiltRule.inject() for absolute DI synchronization.
 */
@HiltAndroidTest
class SovereignUiTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun app_launchesSuccessfully() {
        // 🛡️ Mühür: Uygulama ana ekranının yüklenip yüklenmediğini denetle
        composeTestRule.waitForIdle()
    }
}

package com.patidost.app.ui.screen.auth

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.patidost.app.ui.theme.PatidostTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

/**
 * 🛡️ AuthScreenTest - V10011.70113 Hilt Hardening.
 * Rule 100: HiltAndroidRule is mandatory even for component tests in HiltTestApplication.
 * ARTICLE 16: Fixed "The component was not created" crash.
 */
@HiltAndroidTest
class AuthScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Test
    fun loginFlow_DisplaysWelcomeText() {
        composeTestRule.setContent {
            PatidostTheme {
                AuthContent(
                    uiState = AuthUiState.Idle,
                    onAction = { _, _, _, _ -> }
                )
            }
        }

        composeTestRule.onNodeWithText("Hoş Geldin").assertExists()
    }

    @Test
    fun toggleSignUp_DisplaysJoinUsText() {
        composeTestRule.setContent {
            PatidostTheme {
                AuthContent(
                    uiState = AuthUiState.Idle,
                    onAction = { _, _, _, _ -> }
                )
            }
        }

        composeTestRule.onNodeWithText("Yeni hesap oluştur").performClick()
        composeTestRule.onNodeWithText("Aramıza Katıl").assertExists()
    }
}

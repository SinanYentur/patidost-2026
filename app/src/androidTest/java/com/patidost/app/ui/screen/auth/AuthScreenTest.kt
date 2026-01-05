package com.patidost.app.ui.screen.auth

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.patidost.app.ui.theme.PatidostTheme
import org.junit.Rule
import org.junit.Test

/**
 * AuthScreen Instrumented Test - V45.90.
 * RVWL: Verifying UI element visibility and semantic presence.
 */
class AuthScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun authScreen_elementsVisibility_verified() {
        composeTestRule.setContent {
            PatidostTheme {
                AuthScreen(
                    state = AuthUiState.Unauthenticated,
                    onAuthAction = { _, _, _ -> },
                    onToggleMode = {}
                )
            }
        }

        // Verify Brand Title
        composeTestRule.onNodeWithText("PatiDost").assertIsDisplayed()
        
        // Verify Email Field presence via Label
        composeTestRule.onNodeWithText("Email").assertExists()
        
        // Verify Button presence
        composeTestRule.onNodeWithText("GİRİŞ YAP").assertExists()
    }
}

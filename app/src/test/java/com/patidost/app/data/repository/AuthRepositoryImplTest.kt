package com.patidost.app.data.repository

import android.content.Context
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.patidost.app.util.SecurityGuard
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryImplTest {

    private lateinit var repository: AuthRepositoryImpl
    private val firebaseAuth: FirebaseAuth = mockk(relaxed = true)
    private val context: Context = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        mockkObject(SecurityGuard)
        // Fixed: verifyIntegrity is a suspend function, use coEvery
        coEvery { SecurityGuard.verifyIntegrity(any(), any()) } returns true
        repository = AuthRepositoryImpl(firebaseAuth, context, testDispatcher)
    }

    @Test
    fun `getCurrentUser should emit mapped user when firebase user is present`() = runTest(testDispatcher) {
        val slot = slot<FirebaseAuth.AuthStateListener>()
        val mockFirebaseUser: FirebaseUser = mockk {
            every { uid } returns "test_uid"
            every { email } returns "test@pati.com"
            every { displayName } returns "Test Pati"
        }
        
        every { firebaseAuth.addAuthStateListener(capture(slot)) } answers {
            slot.captured.onAuthStateChanged(firebaseAuth)
        }
        every { firebaseAuth.currentUser } returns mockFirebaseUser

        repository.getCurrentUser().test {
            val user = awaitItem()
            assertThat(user).isNotNull()
            assertThat(user?.id).isEqualTo("test_uid")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCurrentUser should emit null when firebase user is null`() = runTest(testDispatcher) {
        val slot = slot<FirebaseAuth.AuthStateListener>()
        every { firebaseAuth.addAuthStateListener(capture(slot)) } answers {
            slot.captured.onAuthStateChanged(firebaseAuth)
        }
        every { firebaseAuth.currentUser } returns null

        repository.getCurrentUser().test {
            val user = awaitItem()
            assertThat(user).isNull()
            cancelAndIgnoreRemainingEvents()
        }
    }
}

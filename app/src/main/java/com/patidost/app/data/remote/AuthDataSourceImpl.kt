package com.patidost.app.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.remote.dto.UserDto
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthDataSource {

    override suspend fun login(email: String, password: String): Resource<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid
            if (userId != null) {
                Resource.Success(userId)
            } else {
                Resource.Error(UiText.DynamicString("Authentication failed: User ID not found."))
            }
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred during sign-in."))
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Resource<AuthResult> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user
            if (user != null) {
                val isNewUser = result.additionalUserInfo?.isNewUser ?: false
                if (isNewUser) {
                    val userDto = UserDto(
                        id = user.uid,
                        email = user.email ?: "",
                        name = user.displayName ?: "Pati Dostu",
                        status = "active",
                        verificationLevel = 1,
                        avatarUrl = user.photoUrl?.toString()
                    )
                    firestore.collection("users").document(user.uid).set(userDto).await()
                }
                Resource.Success(AuthResult(uid = user.uid, isNewUser = isNewUser))
            } else {
                Resource.Error(UiText.DynamicString("Google Sign-In failed: User ID not found."))
            }
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred during Google Sign-In."))
        }
    }


    override suspend fun signUp(email: String, password: String, name: String): Resource<Unit> {
        var userId: String? = null
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            userId = result.user?.uid
                ?: return Resource.Error(UiText.DynamicString("Registration failed: Could not create auth user."))

            val userDto = UserDto(
                id = userId,
                email = email,
                name = name,
                status = "active",
                verificationLevel = 1
            )
            firestore.collection("users").document(userId).set(userDto).await()

            return Resource.Success(Unit)

        } catch (e: Exception) {
            userId?.let {
                auth.currentUser?.delete()?.await()
            }
            return Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred during sign-up."))
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }
}

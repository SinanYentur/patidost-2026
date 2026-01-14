package com.patidost.app.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.patidost.app.core.util.Resource
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

/**
 * Implementation of AuthDataSource using Firebase Authentication.
 * @param auth The FirebaseAuth instance provided by Hilt.
 */
class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthDataSource {

    override suspend fun signUp(email: String, password: String): Resource<String> {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid
                    if (uid != null) {
                        continuation.resume(Resource.Success(uid))
                    } else {
                        continuation.resume(Resource.Error("An unknown error occurred."))
                    }
                }
                .addOnFailureListener { e ->
                    continuation.resume(Resource.Error(e.message ?: "An unknown error occurred."))
                }
        }
    }

    override suspend fun login(email: String, password: String): Resource<String> {
        return suspendCallableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid
                    if (uid != null) {
                        continuation.resume(Resource.Success(uid))
                    } else {
                        continuation.resume(Resource.Error("An unknown error occurred."))
                    }
                }
                .addOnFailureListener { e ->
                    continuation.resume(Resource.Error(e.message ?: "An unknown error occurred."))
                }
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }
}

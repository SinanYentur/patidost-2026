package com.patidost.app.domain.repository

import com.patidost.app.domain.model.User
import com.patidost.app.domain.util.DomainResult

interface UserRepository {
    suspend fun signIn(email: String, pass: String): DomainResult<User>
    suspend fun signUp(email: String, pass: String, name: String): DomainResult<User>
    fun getCurrentUser(): User?
}

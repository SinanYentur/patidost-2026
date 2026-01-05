package com.patidost.app.data.remote

import com.patidost.app.domain.model.User
import com.patidost.app.domain.util.DomainResult

interface AuthRemoteDataSource {
    suspend fun signIn(email: String, pass: String): DomainResult<User>
    suspend fun signUp(email: String, pass: String, name: String): DomainResult<User>
}

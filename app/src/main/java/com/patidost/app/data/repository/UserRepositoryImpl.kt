package com.patidost.app.data.repository

import com.patidost.app.data.remote.AuthRemoteDataSource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.util.AuthError
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : UserRepository {
    override suspend fun signIn(email: String, pass: String): DomainResult<User> = 
        remoteDataSource.signIn(email, pass)

    override suspend fun signUp(email: String, pass: String, name: String): DomainResult<User> =
        remoteDataSource.signUp(email, pass, name)

    override fun getCurrentUser(): User? = null
}

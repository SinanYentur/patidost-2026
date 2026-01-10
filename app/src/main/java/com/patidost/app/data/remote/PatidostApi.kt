package com.patidost.app.data.remote

import com.patidost.app.data.models.AuthRequest
import com.patidost.app.data.models.AuthResponse
import com.patidost.app.data.models.OnboardingRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class PatidostApi @Inject constructor(private val client: HttpClient) {

    suspend fun signUp(authRequest: AuthRequest): AuthResponse {
        return client.post("http://10.0.2.2:8080/register") {
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }.body()
    }

    suspend fun signIn(authRequest: AuthRequest): AuthResponse {
        return client.post("http://10.0.2.2:8080/login") {
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }.body()
    }

    suspend fun onboardUser(token: String, onboardingRequest: OnboardingRequest) {
        client.post("http://10.0.2.2:8080/user/onboard") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer $token")
            setBody(onboardingRequest)
        }
    }
}

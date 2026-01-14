package com.patidost.app.data.remote

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // This is a simplified version. A real implementation would:
        // 1. Synchronize this block to prevent multiple token refresh calls.
        // 2. Call a "refreshToken" endpoint on your API.
        // 3. Save the new token using TokenManager.
        // 4. Build a new request with the new token.
        
        // For now, we just return null, indicating that we can't refresh the token.
        return null
    }
}

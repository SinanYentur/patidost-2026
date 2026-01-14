package com.patidost.app.domain.usecase.auth

import android.util.Patterns
import com.patidost.app.R
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<User> {
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Resource.Error(UiText.StringResource(R.string.error_validation_email_invalid))
        }
        if (password.length < 6) {
            return Resource.Error(UiText.StringResource(R.string.error_validation_password_too_short))
        }
        return authRepository.signInWithEmail(email, password)
    }
}

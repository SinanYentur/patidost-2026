package com.patidost.app.domain.usecase.auth.validation

import android.util.Patterns

object AuthValidator {
    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun isNameValid(name: String): Boolean {
        return name.isNotBlank()
    }
}

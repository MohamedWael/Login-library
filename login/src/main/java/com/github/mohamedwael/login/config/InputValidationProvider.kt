package com.github.mohamedwael.login.config

import androidx.core.util.PatternsCompat

interface InputValidationProvider {

    fun isUsernameValid(username: String?): Boolean

    fun isPasswordValid(password: String?): Boolean

    fun isEmailValid(email: String?): Boolean {
        if (email == null) return false
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}
package com.github.mohamedwael.loginsample.authorization

import com.github.mohamedwael.login.config.InputValidationProvider

class AppInputValidationProvider : InputValidationProvider {
    override fun isUsernameValid(phone: String?): Boolean {
        if (phone == null) return false
        if (phone.length < 11 || phone.length > 14) return false
        return true
    }

    override fun isPasswordValid(password: String?): Boolean {
        if (password == null) return false
        if (password.length < 6) return false
        return true
    }
}
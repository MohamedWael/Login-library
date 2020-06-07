package com.github.mohamedwael.login.passwordlogin

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler

interface PasswordLogin {
    fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    )
}
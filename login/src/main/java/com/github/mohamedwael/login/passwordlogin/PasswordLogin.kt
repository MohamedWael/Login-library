package com.github.mohamedwael.login.passwordlogin

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import java.io.Serializable

interface PasswordLogin {
    fun <T : Serializable> login(
        username: String,
        password: String,
        onSuccess: (response: T?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    )
}
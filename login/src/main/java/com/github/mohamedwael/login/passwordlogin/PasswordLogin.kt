package com.github.mohamedwael.login.passwordlogin

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import java.io.Serializable

interface PasswordLogin {
    fun login(
        username: String,
        password: String,
        onSuccess: (response: Any?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    )
}
package com.github.mohamedwael.login.passwordlogin

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import java.io.Serializable

const val BROADCAST_ACTION_LOGIN_SUCCESS = "com.github.mohamedwael.login.success"
const val BROADCAST_DATA_LOGIN_SUCCESS = "com.github.mohamedwael.login.success.data"

interface PasswordLogin {
    fun <T : Serializable> login(
        username: String,
        password: String,
        onSuccess: (response: T?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    )
}
package com.github.mohamedwael.loginsample.authorization

import android.util.Log
import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import com.github.mohamedwael.login.passwordlogin.PasswordLogin

class PasswordLoginImpl : PasswordLogin {
    override fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    ) {
        Log.d("PasswordLogin", "$username,  $password")
        onSuccess()
    }

}
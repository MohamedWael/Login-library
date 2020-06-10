package com.github.mohamedwael.loginsample.authorization

import android.util.Log
import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import com.github.mohamedwael.login.passwordlogin.PasswordLogin
import java.io.Serializable

class PasswordLoginImpl : PasswordLogin {
    override fun <T : Serializable> login(
        username: String,
        password: String,
        onSuccess: (response:T?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    ) {
        Log.d("PasswordLogin", "$username,  $password")
        onSuccess(null)
    }

}
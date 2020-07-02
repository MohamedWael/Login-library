package com.github.mohamedwael.loginsample.authorization

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import com.blogspot.mowael.utilslibrary.Logger
import com.github.mohamedwael.login.verificationcodelogin.usernamevalidation.VerificationProvider

const val INVALID_USER_NAME = "01063603530"

class VerificationProviderImpl : VerificationProvider {
    override fun sendVerificationCode(
        userName: String,
        onSuccess: (String) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    ) {
        fun isUsernameValid(username: String): Boolean {
            if (username == INVALID_USER_NAME) return false
            return true
        }
        if (isUsernameValid(userName)) {
            Logger.d(userName)
            onSuccess("")
        } else {
            onError(object : ErrorMessageHandler {
                override fun getMessage(): String = "invalid username"
                override fun getMessageRes(): Int = 0
            })
        }


    }
}
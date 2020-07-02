package com.github.mohamedwael.login.verificationcodelogin.usernamevalidation

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler

interface VerificationProvider {
    fun sendVerificationCode(userName: String, onSuccess: (smsCode:String) -> Unit, onError: (ErrorMessageHandler) -> Unit)
}
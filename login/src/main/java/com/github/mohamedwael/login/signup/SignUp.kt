package com.github.mohamedwael.login.signup

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler

interface SignUp {
    fun signUp(
        username: String,
        password: String,
        onSuccess: (response: Any?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    )

    fun onTermsClick()
}
package com.github.mohamedwael.login.signup

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler

interface SignUp {
    fun signUp(
        user:SigningUpUser,
        onSuccess: (response: Any?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    )

    fun onTermsAndConditionsClick()
    fun onPrivacyPolicyClick()
}
package com.github.mohamedwael.loginsample.authorization

import com.blogspot.mowael.baselibrary.contract.ErrorMessageHandler
import com.github.mohamedwael.login.signup.SignUp
import com.github.mohamedwael.login.signup.SigningUpUser

class SignUpImpl(
    private val onTermsAndConditionsClick: () -> Unit,
    private val onPrivacyPolicyClick: () -> Unit
) : SignUp {
    override fun signUp(
        user: SigningUpUser,
        onSuccess: (response: Any?) -> Unit,
        onError: (ErrorMessageHandler) -> Unit
    ) {
        if (user.username != INVALID_USER_NAME) {
            onError(object : ErrorMessageHandler {
                override fun getMessage(): String = "invalid username"
                override fun getMessageRes(): Int = 0
            })
        } else {
            onSuccess("")
        }
    }

    override fun onTermsAndConditionsClick() {
        this.onTermsAndConditionsClick.invoke()
    }

    override fun onPrivacyPolicyClick() {
        this.onPrivacyPolicyClick.invoke()
    }
}